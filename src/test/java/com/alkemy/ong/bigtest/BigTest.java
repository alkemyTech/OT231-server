package com.alkemy.ong.bigtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.alkemy.ong.OngApplication;
import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import com.alkemy.ong.infrastructure.database.entity.RoleEntity;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.repository.spring.ICategorySpringRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.ICommentSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.IMemberSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.INewsSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.IOrganizationSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.IRoleSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.IUserSpringRepository;
import com.alkemy.ong.infrastructure.rest.request.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "spring.main.allow-bean-definition-overriding=true",
    classes = OngApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class BigTest {

  private static final String ADMIN_EMAIL = "jason@voorhees.com";
  private static final String USER_EMAIL = "freedy@krueger.com";
  private static final String PASSWORD = "abcd1234";

  @Autowired
  protected MockMvc mockMvc;

  @Autowired
  protected PasswordEncoder passwordEncoder;

  @Autowired
  protected ObjectMapper objectMapper;

  @Autowired
  protected IUserSpringRepository userRepository;

  @Autowired
  protected IRoleSpringRepository roleRepository;

  @Autowired
  protected INewsSpringRepository newsRepository;

  @Autowired
  protected IMemberSpringRepository memberRepository;

  @Autowired
  protected ICategorySpringRepository categoryRepository;

  @Autowired
  protected IOrganizationSpringRepository organizationRepository;

  @Autowired
  protected ICommentSpringRepository commentRepository;

  @Before
  public void setup() {
    createRoles();
    createUserData();
    createNewsCategory();
  }

  @After
  public void tearDown() {
    deleteAllEntities();
  }

  protected UserEntity getRandomUser() {
    return userRepository.findByEmail(USER_EMAIL);
  }

  private void deleteAllEntities() {
    commentRepository.deleteAll();
    newsRepository.deleteAll();
    memberRepository.deleteAll();
    organizationRepository.deleteAll();
  }

  private void createUserData() {
    if (userRepository.findByEmail(ADMIN_EMAIL) == null) {
      saveAdminUser();
    }
    if (userRepository.findByEmail(USER_EMAIL) == null) {
      saveStandardUser();
    }
  }

  private void createRoles() {
    if (roleRepository.count() == 0) {
      roleRepository.saveAll(List.of(
          buildRole(Role.USER),
          buildRole(Role.ADMIN)));
    }
  }

  private void createNewsCategory() {
    if (categoryRepository.findByNameIgnoreCase("news") == null) {
      categoryRepository.save(buildCategory("news"));
    }
  }

  private void saveStandardUser() {
    userRepository.save(buildUser(
        "Freddy",
        "Krueger",
        USER_EMAIL,
        Role.USER));
  }

  private void saveAdminUser() {
    userRepository.save(buildUser(
        "Jason",
        "Voorhees",
        ADMIN_EMAIL,
        Role.ADMIN));
  }

  protected NewsEntity saveNews() {
    return newsRepository.save(NewsEntity.builder()
        .image("https://s3.com/news.jpg")
        .content("News content.")
        .name("My first News!!")
        .softDelete(false)
        .build());
  }

  private UserEntity buildUser(String firstName, String lastName, String email, Role role) {
    return UserEntity.builder()
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .password(passwordEncoder.encode(PASSWORD))
        .role(roleRepository.findByName(role.getFullRoleName()))
        .softDelete(false)
        .build();
  }

  private RoleEntity buildRole(Role role) {
    return RoleEntity.builder()
        .description(role.name())
        .name(role.getFullRoleName())
        .build();
  }

  private CategoryEntity buildCategory(String name) {
    return CategoryEntity.builder()
        .name(name)
        .build();
  }

  protected String getAuthorizationTokenForAdminUser() throws Exception {
    return getAuthorizationTokenForUser(ADMIN_EMAIL);
  }

  protected String getAuthorizationTokenForStandardUser() throws Exception {
    return getAuthorizationTokenForUser(USER_EMAIL);
  }

  private String getAuthorizationTokenForUser(String email) throws Exception {
    String content = mockMvc.perform(post("/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(AuthenticationRequest.builder()
            .email(email)
            .password(PASSWORD)
            .build()))).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    return JsonPath.read(content, "$.token");
  }

  protected MemberEntity getRandomMember() {
    return memberRepository.save(MemberEntity.builder()
        .name("Charles Lee Ray")
        .image("https://s3.com/chucky.jpg")
        .softDelete(false)
        .build());
  }

  protected Long saveOrganization() {
    OrganizationEntity organizationEntity = organizationRepository.save(OrganizationEntity.builder()
        .name("Somos Mas")
        .image("https://s3.com/logo.jpg/")
        .welcomeText("Welcome to Somos Mas")
        .email("somos.mas@ong.com")
        .phone("+540303456")
        .address("Elm Street 3")
        .facebookUrl("https://www.facebook.com/Somos_Mas/")
        .linkedInUrl("https://www.linkedin.com/in/Somos-Mas/")
        .instagramUrl("https://www.instagram.com/SomosMas/")
        .softDelete(false)
        .build());

    return organizationEntity.getId();
  }
}
