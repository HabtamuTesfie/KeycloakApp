import { Injectable } from "@angular/core";
import { NgZone } from "@angular/core";

interface StorageWrapper {
	get<T>( key: string ) : Promise<T | null>;
	remove( key: string ) : void;
	set( key: string, value: any ) : void;
}

interface StorageCache {
	[ key: string ]: any;
}

@Injectable({
	providedIn: "root"
})
export class TemporaryStorageService {

	private storage: StorageWrapper;

	constructor( zone: NgZone ) {

		this.storage = ( window.sessionStorage )
			? new SessionStorageWrapper( zone )
			: new InMemoryWrapper()
		;

	}

	public forKey( key: string ) : TemporaryStorageFacet {

		return( new TemporaryStorageFacet( key, this.storage ) );

	}

	public get<T>( key: string ) : Promise<T | null> {

		return( this.storage.get<T>( key ) );

	}

	public remove( key: string ) : void {

		this.storage.remove( key );

	}


	// I store the given value with the given key.
	public set( key: string, value: any ) : void {

		this.storage.set( key, value );

	}

}


export class TemporaryStorageFacet {

	private key: string;
	private storage: StorageWrapper;
	constructor( key: string, storage: StorageWrapper ) {

		this.key = key;
		this.storage = storage;

	}

	public get<T>() : Promise<T | null> {

		return( this.storage.get<T>( this.key ) );

	}

	public remove() : void {

		this.storage.remove( this.key );

	}


	// I store the given value with the locked-in key.
	public set( value: any ) : void {

		this.storage.set( this.key, value );

	}

}

class SessionStorageWrapper implements StorageWrapper {

	private debounceDuration: number;
	private cache: StorageCache;
	private storageKey: string;
	private timerID: any;
	private zone: NgZone;

	constructor( zone: NgZone ) {

		this.zone = zone;
		this.cache = Object.create( null );
		this.storageKey = "temp_session_storage";
		this.debounceDuration = 1000; // 1-second.
		this.timerID = 0;
		this.loadFromCache();

	}

	public async get<T>( key: string ) : Promise<T | null> {

		return( <T>this.cache[ key ] ?? null );

	}

	public remove( key: string ) : void {

		if ( key in this.cache ) {

			delete( this.cache[ key ] );
			this.persistToCache();

		}

	}


	// I store the given value with the given key.
	public set( key: string, value: any ) : void {

		this.cache[ key ] = value;
		this.persistToCache();

	}

	// ---
	// PRIVATE METHOD.
	// ---

	// I debounce invocations of the given callback outside of the Angular zone.
	private debounceOutsideNgZone( callback: Function ) : void {

		this.zone.runOutsideAngular(
			() => {

				clearTimeout( this.timerID );

				this.timerID = setTimeout(
					( )=> {

						this.timerID = 0;
						callback();

					},
					this.debounceDuration
				);

			}
		);

	}

	private loadFromCache() : void {

		try {

			var serializedCache = sessionStorage.getItem( this.storageKey );

			if ( serializedCache ) {

				Object.assign( this.cache, <StorageCache>JSON.parse( serializedCache ) );

			}

		} catch ( error ) {

			console.warn( "SessionStorageWrapper was unable to read from SessionStorage API." );
			console.error( error );

		}

	}

	private persistToCache() : void {
		this.debounceOutsideNgZone(
			() => {

				console.warn( "Flushing to SessionStorage API." );
				try {

					sessionStorage.setItem( this.storageKey, JSON.stringify( this.cache ) );

				} catch ( error ) {
					
					console.warn( "SessionStorageWrapper was unable to write to SessionStorage API." );
					console.error( error );

				}

			}
		);

	}

}
class InMemoryWrapper implements StorageWrapper {

	private cache: StorageCache;

	// I initialize an in-memory implementation of the storage wrapper.
	constructor() {

		this.cache = Object.create( null );

	}

	// ---
	// PUBLIC METHODS.
	// ---

	// I get the value associated with the given key; or null if the key is undefined.
	public async get<T>( key: string ) : Promise<T | null> {

		return( <T>this.cache[ key ] ?? null );

	}


	// I remove any value associated with the given key.
	public remove( key: string ) : void {

		delete( this.cache[ key ] );

	}


	// I store the given value with the given key.
	public set( key: string, value: any ) : void {

		this.cache[ key ] = value;

	}

}