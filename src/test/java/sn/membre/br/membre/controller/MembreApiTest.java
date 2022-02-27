package sn.membre.br.membre.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.hamcrest.Matchers.*;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import sn.membre.br.membre.model.Membre;
import sn.membre.br.membre.service.MemberService;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(MembreApiImp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MembreApiTest {
    private static final String  ulr_api_member="/membre_br/membre/all";
    private static final String  ulr_api_post_member="/membre_br/membre";
    private static final String  ulr_api_update_member="/membre_br/membre/1";
    private static final String  ulr_api_delete_member="/membre_br/membre/1";
    private static final String  ulr_api_get_member_id="/membre_br/membre/1";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MemberService memberService;
    private ObjectMapper ob=new ObjectMapper();


    @Test
    public void testCreatedMembre() throws Exception {
        String json=ob.writeValueAsString(createMembre());
        MockHttpServletResponse response=mockMvc.perform(post(ulr_api_post_member)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andReturn().getResponse();
        //tester la methode creation de membre
        MatcherAssert.assertThat(response.getStatus(),is(HttpStatus.CREATED.value()));

    }
    @Test
    public void testgetMembreById() throws Exception {
        MockHttpServletResponse response=mockMvc.perform(get(ulr_api_get_member_id)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //test si la reponse est ok
        MatcherAssert.assertThat(response.getStatus(), is(HttpStatus.OK.value()));

    }
    @Test
    public void testupdateMembre() throws Exception {
        String  json=ob.writeValueAsString(createMembre());
        MockHttpServletResponse response=mockMvc.perform(put(ulr_api_update_member)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andReturn().getResponse();
        //tester la methode creation de membre
        MatcherAssert.assertThat(response.getStatus(),is(HttpStatus.OK.value()));

    }
    @Test
    public void testdeleteMembre() throws Exception {
        MockHttpServletResponse response=mockMvc.perform(delete(ulr_api_delete_member)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //tester la methode creation de membre
        MatcherAssert.assertThat(response.getStatus(),is(HttpStatus.OK.value()));

    }
    @Test
    public void testgetMembreByAll() throws Exception {
        MockHttpServletResponse response=mockMvc.perform(get(ulr_api_member)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //test si la reponse est ok
        MatcherAssert.assertThat(response.getStatus(), is(HttpStatus.OK.value()));

    }
    private
    Membre createMembre(){
        Membre result=new Membre();
        result.setId(1L);
        result.setNom("modou");
        result.setPrenom("fatou");
        result.setLieudenaissance("barale");
        return  result;
    }
}
