package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Pay;
import com.mycompany.myapp.repository.PayRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.util.Configuration;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;


import org.xml.sax.SAXException;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.Map;
import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;



import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.sdk.exceptions.PayPalException;
import liquibase.exception.InvalidChangeDefinitionException;
import java.io.IOException;

import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsReq;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.GetExpressCheckoutDetailsResponseDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Pay}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PayResource {

    private final Logger log = LoggerFactory.getLogger(PayResource.class);

    private static final String ENTITY_NAME = "pay";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    @Value("${spring.application.RedirectURL}")
    private String redirectURL;

    @Value("${spring.application.returnURL}")
    private String returnURL;

    @Value("${spring.application.cancelURL}")
    private String cancelURL;
    private final PayRepository payRepository;
    private @Valid Pay payAmount;


    public PayResource(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

    /**
     * {@code POST  /pays} : Create a new pay.
     *
     * @param pay the pay to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pay, or with status {@code 400 (Bad Request)} if the pay has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/amountOfMoney")
    public String getPayment(@Valid @RequestBody Pay pay) throws URISyntaxException {
        this.payAmount = pay;
        return "payAmount";
    }

    SetExpressCheckoutResponseType setExpressCheckoutResponse;

    @GetMapping("/paypal")
    public String setExpressCheckout()
       // throws PayPalException, XPathExpressionException, ClientActionRequiredException, SSLConfigurationException, MissingCredentialException, InvalidResponseDataException, InvalidCredentialException, IOException, ParsedNodeException, HttpErrorException, SAXException, ParserConfigurationException, InvalidChangeDefinitionException
       throws  SSLConfigurationException,PayPalException,InvalidCredentialException,IOException
       ,HttpErrorException,SAXException,InvalidResponseDataException,ParserConfigurationException,ClientActionRequiredException
       ,InvalidChangeDefinitionException, MissingCredentialException, InterruptedException{
        String url = System.getProperty("url",this.redirectURL);
        String returnURLi = System.getProperty("returnURL",this.returnURL);
        String cancelURLi = System.getProperty("cancelURL",this.cancelURL);

        Long payerId = 5l;
        String paymentAmount = payAmount.getPaymentAmount().toString();
        // String returnURL = "http://localhost:9000/payment-save";
        // String cancelURL = "http://localhost:9000/";
        PaymentActionCodeType paymentAction = PaymentActionCodeType.SALE;
        CurrencyCodeType currencyCode = CurrencyCodeType.EUR;

        Map<String, String> configurationMap = Configuration.getAcctAndConfig();
        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
        SetExpressCheckoutRequestType setExpressCheckoutReq = new SetExpressCheckoutRequestType();
        setExpressCheckoutReq.setVersion("63.0");
        SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();

        PaymentDetailsType paymentDetails = new PaymentDetailsType();
        paymentDetails.setOrderDescription("PayGove integration with paypal");
        paymentDetails.setInvoiceID("INVOICE-" + Math.random());
        BasicAmountType orderTotal = new BasicAmountType();
        orderTotal.setValue(paymentAmount);
        orderTotal.setCurrencyID(currencyCode);
        paymentDetails.setOrderTotal(orderTotal);
        paymentDetails.setPaymentAction(paymentAction);
        details.setPaymentDetails(Arrays.asList(new PaymentDetailsType[] { paymentDetails }));
        details.setReturnURL(returnURLi);
        details.setCancelURL(cancelURLi);
        details.setCustom(payerId.toString());

        setExpressCheckoutReq.setSetExpressCheckoutRequestDetails(details);

        SetExpressCheckoutReq expressCheckoutReq = new SetExpressCheckoutReq();
        expressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutReq);

        setExpressCheckoutResponse = service.setExpressCheckout(expressCheckoutReq);
        getExpressCheckoutDetails(setExpressCheckoutResponse.getToken());

        String redirectURLi =(url + setExpressCheckoutResponse.getToken());

     return JSONObject.quote(redirectURLi);
    }

    public String startgetExpressResponse2()
        throws ClientActionRequiredException, SSLConfigurationException, MissingCredentialException, OAuthException, InvalidResponseDataException, InvalidCredentialException, IOException, ParserConfigurationException, HttpErrorException, InterruptedException, SAXException {
        try {
            getExpressCheckoutDetails(setExpressCheckoutResponse.getToken());
        }  catch (PayPalException e) {

            e.printStackTrace();
        }
        return "GET is started";
    }

    @GetMapping("/paypalGetChechout")
    public String startDoExpresResponse()
        throws ClientActionRequiredException, InvalidChangeDefinitionException, SSLConfigurationException, MissingCredentialException, InvalidResponseDataException, InvalidCredentialException, IOException, ParserConfigurationException, HttpErrorException, InterruptedException, SAXException, PayPalException {
        doExpressResponse(getExpressCheckoutDetails(setExpressCheckoutResponse.getToken()));

        return "SUCCESS PAYMENT WITH TRANSACTION ID =" + getExpressCheckoutDetails(setExpressCheckoutResponse.getToken());
    }

    public GetExpressCheckoutDetailsResponseDetailsType getExpressCheckoutDetails(String token)
        throws PayPalException,  SAXException, ParserConfigurationException, SSLConfigurationException,
           HttpErrorException, InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, IOException, InterruptedException, InvalidCredentialException
         {

        Map<String, String> configurationMap = Configuration.getAcctAndConfig();

        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

        GetExpressCheckoutDetailsReq grequest = new GetExpressCheckoutDetailsReq();
        GetExpressCheckoutDetailsRequestType pprequest = new GetExpressCheckoutDetailsRequestType();
        pprequest.setVersion("63.0");


        grequest.setGetExpressCheckoutDetailsRequest(new GetExpressCheckoutDetailsRequestType(token));
        GetExpressCheckoutDetailsResponseType ppresponse = service.getExpressCheckoutDetails(grequest);

        ppresponse.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID();
        ppresponse.getGetExpressCheckoutDetailsResponseDetails().getToken();
        ppresponse.getAck();
        ppresponse.getGetExpressCheckoutDetailsResponseDetails();


        log.info(ppresponse.getGetExpressCheckoutDetailsResponseDetails().getPayerInfo().getPayerID() + " payerID");
        log.info(ppresponse.getAck() + " ack");
        log.info(ppresponse.getGetExpressCheckoutDetailsResponseDetails().getToken() + " TOKEN");
        log.info(ppresponse.getGetExpressCheckoutDetailsResponseDetails().getPaymentDetails() + " PAYMENTDETAILS");
        log.info(ppresponse.getGetExpressCheckoutDetailsResponseDetails().getPaymentInfo() + " PAYMENTINFO");
        log.info(ppresponse.getGetExpressCheckoutDetailsResponseDetails().getBillingAddress() + " BILLINGADRESS");
        log.info(ppresponse.getGetExpressCheckoutDetailsResponseDetails().getCheckoutStatus() + " CHECKOUTSTATUS");

        return ppresponse.getGetExpressCheckoutDetailsResponseDetails();
    }

    public void doExpressResponse(GetExpressCheckoutDetailsResponseDetailsType response)
        throws PayPalException,  SAXException, ParserConfigurationException,
         SSLConfigurationException,HttpErrorException, InvalidResponseDataException,
         ClientActionRequiredException,
          MissingCredentialException,  IOException, InterruptedException,
          InvalidCredentialException {
        Map<String, String> configurationMap = Configuration.getAcctAndConfig();
        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

        DoExpressCheckoutPaymentRequestType pprequest = new DoExpressCheckoutPaymentRequestType();
        pprequest.setVersion("63.0");
      DoExpressCheckoutPaymentRequestDetailsType paymentDetailsRequestType = new DoExpressCheckoutPaymentRequestDetailsType();

        paymentDetailsRequestType.setPaymentDetails(response.getPaymentDetails());
        paymentDetailsRequestType.setToken(response.getToken());
        paymentDetailsRequestType.setPayerID(response.getPayerInfo().getPayerID());
        paymentDetailsRequestType.setPaymentAction(PaymentActionCodeType.SALE);
        pprequest.setDoExpressCheckoutPaymentRequestDetails(paymentDetailsRequestType);
        DoExpressCheckoutPaymentReq payRequest1 = new DoExpressCheckoutPaymentReq();
        payRequest1.setDoExpressCheckoutPaymentRequest(pprequest);
        DoExpressCheckoutPaymentResponseType ppresponse = service.doExpressCheckoutPayment(payRequest1);

        System.out.println(ppresponse.getAck() + " PAYMENT");

    }

    @PostMapping("/pays")
    public ResponseEntity<Pay> createPay(@RequestBody Pay pay) throws URISyntaxException {
        log.debug("REST request to save Pay : {}", pay);
        if (pay.getId() != null) {
            throw new BadRequestAlertException("A new pay cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Pay result = payRepository.save(pay);
        return ResponseEntity
            .created(new URI("/api/pays/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pays/:id} : Updates an existing pay.
     *
     * @param id the id of the pay to save.
     * @param pay the pay to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pay,
     * or with status {@code 400 (Bad Request)} if the pay is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pay couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pays/{id}")
    public ResponseEntity<Pay> updatePay(@PathVariable(value = "id", required = false) final Long id, @RequestBody Pay pay)
        throws URISyntaxException {
        log.debug("REST request to update Pay : {}, {}", id, pay);
        if (pay.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pay.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Pay result = payRepository.save(pay);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pay.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pays/:id} : Partial updates given fields of an existing pay, field will ignore if it is null
     *
     * @param id the id of the pay to save.
     * @param pay the pay to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pay,
     * or with status {@code 400 (Bad Request)} if the pay is not valid,
     * or with status {@code 404 (Not Found)} if the pay is not found,
     * or with status {@code 500 (Internal Server Error)} if the pay couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pays/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Pay> partialUpdatePay(@PathVariable(value = "id", required = false) final Long id, @RequestBody Pay pay)
        throws URISyntaxException {
        log.debug("REST request to partial update Pay partially : {}, {}", id, pay);
        if (pay.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pay.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Pay> result = payRepository
            .findById(pay.getId())
            .map(existingPay -> {
                if (pay.getCik() != null) {
                    existingPay.setCik(pay.getCik());
                }
                if (pay.getCcc() != null) {
                    existingPay.setCcc(pay.getCcc());
                }
                if (pay.getPaymentAmount() != null) {
                    existingPay.setPaymentAmount(pay.getPaymentAmount());
                }
                if (pay.getName() != null) {
                    existingPay.setName(pay.getName());
                }
                if (pay.getEmail() != null) {
                    existingPay.setEmail(pay.getEmail());
                }
                if (pay.getPhone() != null) {
                    existingPay.setPhone(pay.getPhone());
                }
                if (pay.getApproval() != null) {
                    existingPay.setApproval(pay.getApproval());
                }

                return existingPay;
            })
            .map(payRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pay.getId().toString())
        );
    }

    /**
     * {@code GET  /pays} : get all the pays.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pays in body.
     */
    @GetMapping("/pays")
    public List<Pay> getAllPays() {
        log.debug("REST request to get all Pays");
        return payRepository.findAll();
    }

    /**
     * {@code GET  /pays/:id} : get the "id" pay.
     *
     * @param id the id of the pay to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pay, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pays/{id}")
    public ResponseEntity<Pay> getPay(@PathVariable Long id) {
        log.debug("REST request to get Pay : {}", id);
        Optional<Pay> pay = payRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pay);
    }

    /**
     * {@code DELETE  /pays/:id} : delete the "id" pay.
     *
     * @param id the id of the pay to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pays/{id}")
    public ResponseEntity<Void> deletePay(@PathVariable Long id) {
        log.debug("REST request to delete Pay : {}", id);
        payRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
