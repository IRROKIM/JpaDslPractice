package hellojpa;

import com.example.jpadsl.JpaDslApplication;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest(classes = JpaDslApplication.class)
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("테스트")
    @Test
    public void createMember() throws Exception {
        String url = "/members";

        JSONObject obj = new JSONObject();
        obj.put("id", "hong");
        obj.put("name", "hone gil dong");

        System.out.println("obj:" + obj.toString());


        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(obj.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
            MockHttpServletResponse response = result.getResponse();
            String userCode = response.getContentAsString();
            System.out.println("userCode:" + userCode);
            // 사용자 등록 성공 여부 확인.
            Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

            mockMvc.perform(MockMvcRequestBuilders.get(url + "/" + userCode)
                    .content(obj.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(result2 -> {
                MockHttpServletResponse response2 = result2.getResponse();
                String userCode2 = response2.getContentAsString();
                System.out.println("userCode2:" + userCode2);
                System.out.println(response2.getStatus());
                // 사용자 정보구하기 성공 여부 확인.
                Assertions.assertEquals(HttpStatus.OK.value(), response2.getStatus());
            });
        });
    }
}
