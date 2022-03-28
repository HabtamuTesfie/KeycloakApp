package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Pay;
import com.mycompany.myapp.repository.PayRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PayResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PayResourceIT {

    private static final String DEFAULT_CIK = "AAAAAAAAAA";
    private static final String UPDATED_CIK = "BBBBBBBBBB";

    private static final String DEFAULT_CCC = "AAAAAAAAAA";
    private static final String UPDATED_CCC = "BBBBBBBBBB";

    private static final Double DEFAULT_PAYMENT_AMOUNT = 1D;
    private static final Double UPDATED_PAYMENT_AMOUNT = 2D;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVAL = "AAAAAAAAAA";
    private static final String UPDATED_APPROVAL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pays";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPayMockMvc;

    private Pay pay;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pay createEntity(EntityManager em) {
        Pay pay = new Pay()
            .cik(DEFAULT_CIK)
            .ccc(DEFAULT_CCC)
            .paymentAmount(DEFAULT_PAYMENT_AMOUNT)
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE)
            .approval(DEFAULT_APPROVAL);
        return pay;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pay createUpdatedEntity(EntityManager em) {
        Pay pay = new Pay()
            .cik(UPDATED_CIK)
            .ccc(UPDATED_CCC)
            .paymentAmount(UPDATED_PAYMENT_AMOUNT)
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .approval(UPDATED_APPROVAL);
        return pay;
    }

    @BeforeEach
    public void initTest() {
        pay = createEntity(em);
    }

    @Test
    @Transactional
    void createPay() throws Exception {
        int databaseSizeBeforeCreate = payRepository.findAll().size();
        // Create the Pay
        restPayMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pay))
            )
            .andExpect(status().isCreated());

        // Validate the Pay in the database
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeCreate + 1);
        Pay testPay = payList.get(payList.size() - 1);
        assertThat(testPay.getCik()).isEqualTo(DEFAULT_CIK);
        assertThat(testPay.getCcc()).isEqualTo(DEFAULT_CCC);
        assertThat(testPay.getPaymentAmount()).isEqualTo(DEFAULT_PAYMENT_AMOUNT);
        assertThat(testPay.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPay.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPay.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testPay.getApproval()).isEqualTo(DEFAULT_APPROVAL);
    }

    @Test
    @Transactional
    void createPayWithExistingId() throws Exception {
        // Create the Pay with an existing ID
        pay.setId(1L);

        int databaseSizeBeforeCreate = payRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pay))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pay in the database
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPays() throws Exception {
        // Initialize the database
        payRepository.saveAndFlush(pay);

        // Get all the payList
        restPayMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pay.getId().intValue())))
            .andExpect(jsonPath("$.[*].cik").value(hasItem(DEFAULT_CIK)))
            .andExpect(jsonPath("$.[*].ccc").value(hasItem(DEFAULT_CCC)))
            .andExpect(jsonPath("$.[*].paymentAmount").value(hasItem(DEFAULT_PAYMENT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].approval").value(hasItem(DEFAULT_APPROVAL)));
    }

    @Test
    @Transactional
    void getPay() throws Exception {
        // Initialize the database
        payRepository.saveAndFlush(pay);

        // Get the pay
        restPayMockMvc
            .perform(get(ENTITY_API_URL_ID, pay.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pay.getId().intValue()))
            .andExpect(jsonPath("$.cik").value(DEFAULT_CIK))
            .andExpect(jsonPath("$.ccc").value(DEFAULT_CCC))
            .andExpect(jsonPath("$.paymentAmount").value(DEFAULT_PAYMENT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.approval").value(DEFAULT_APPROVAL));
    }

    @Test
    @Transactional
    void getNonExistingPay() throws Exception {
        // Get the pay
        restPayMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPay() throws Exception {
        // Initialize the database
        payRepository.saveAndFlush(pay);

        int databaseSizeBeforeUpdate = payRepository.findAll().size();

        // Update the pay
        Pay updatedPay = payRepository.findById(pay.getId()).get();
        // Disconnect from session so that the updates on updatedPay are not directly saved in db
        em.detach(updatedPay);
        updatedPay
            .cik(UPDATED_CIK)
            .ccc(UPDATED_CCC)
            .paymentAmount(UPDATED_PAYMENT_AMOUNT)
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .approval(UPDATED_APPROVAL);

        restPayMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPay.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPay))
            )
            .andExpect(status().isOk());

        // Validate the Pay in the database
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeUpdate);
        Pay testPay = payList.get(payList.size() - 1);
        assertThat(testPay.getCik()).isEqualTo(UPDATED_CIK);
        assertThat(testPay.getCcc()).isEqualTo(UPDATED_CCC);
        assertThat(testPay.getPaymentAmount()).isEqualTo(UPDATED_PAYMENT_AMOUNT);
        assertThat(testPay.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPay.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPay.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testPay.getApproval()).isEqualTo(UPDATED_APPROVAL);
    }

    @Test
    @Transactional
    void putNonExistingPay() throws Exception {
        int databaseSizeBeforeUpdate = payRepository.findAll().size();
        pay.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pay.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pay))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pay in the database
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPay() throws Exception {
        int databaseSizeBeforeUpdate = payRepository.findAll().size();
        pay.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(pay))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pay in the database
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPay() throws Exception {
        int databaseSizeBeforeUpdate = payRepository.findAll().size();
        pay.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayMockMvc
            .perform(
                put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(pay))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Pay in the database
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePayWithPatch() throws Exception {
        // Initialize the database
        payRepository.saveAndFlush(pay);

        int databaseSizeBeforeUpdate = payRepository.findAll().size();

        // Update the pay using partial update
        Pay partialUpdatedPay = new Pay();
        partialUpdatedPay.setId(pay.getId());

        partialUpdatedPay.cik(UPDATED_CIK).paymentAmount(UPDATED_PAYMENT_AMOUNT).approval(UPDATED_APPROVAL);

        restPayMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPay.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPay))
            )
            .andExpect(status().isOk());

        // Validate the Pay in the database
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeUpdate);
        Pay testPay = payList.get(payList.size() - 1);
        assertThat(testPay.getCik()).isEqualTo(UPDATED_CIK);
        assertThat(testPay.getCcc()).isEqualTo(DEFAULT_CCC);
        assertThat(testPay.getPaymentAmount()).isEqualTo(UPDATED_PAYMENT_AMOUNT);
        assertThat(testPay.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPay.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPay.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testPay.getApproval()).isEqualTo(UPDATED_APPROVAL);
    }

    @Test
    @Transactional
    void fullUpdatePayWithPatch() throws Exception {
        // Initialize the database
        payRepository.saveAndFlush(pay);

        int databaseSizeBeforeUpdate = payRepository.findAll().size();

        // Update the pay using partial update
        Pay partialUpdatedPay = new Pay();
        partialUpdatedPay.setId(pay.getId());

        partialUpdatedPay
            .cik(UPDATED_CIK)
            .ccc(UPDATED_CCC)
            .paymentAmount(UPDATED_PAYMENT_AMOUNT)
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .approval(UPDATED_APPROVAL);

        restPayMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPay.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPay))
            )
            .andExpect(status().isOk());

        // Validate the Pay in the database
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeUpdate);
        Pay testPay = payList.get(payList.size() - 1);
        assertThat(testPay.getCik()).isEqualTo(UPDATED_CIK);
        assertThat(testPay.getCcc()).isEqualTo(UPDATED_CCC);
        assertThat(testPay.getPaymentAmount()).isEqualTo(UPDATED_PAYMENT_AMOUNT);
        assertThat(testPay.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPay.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPay.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testPay.getApproval()).isEqualTo(UPDATED_APPROVAL);
    }

    @Test
    @Transactional
    void patchNonExistingPay() throws Exception {
        int databaseSizeBeforeUpdate = payRepository.findAll().size();
        pay.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pay.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pay))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pay in the database
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPay() throws Exception {
        int databaseSizeBeforeUpdate = payRepository.findAll().size();
        pay.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pay))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pay in the database
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPay() throws Exception {
        int databaseSizeBeforeUpdate = payRepository.findAll().size();
        pay.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(pay))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Pay in the database
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePay() throws Exception {
        // Initialize the database
        payRepository.saveAndFlush(pay);

        int databaseSizeBeforeDelete = payRepository.findAll().size();

        // Delete the pay
        restPayMockMvc
            .perform(delete(ENTITY_API_URL_ID, pay.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Pay> payList = payRepository.findAll();
        assertThat(payList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
