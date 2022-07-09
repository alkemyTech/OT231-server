package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.service.AuthenticationService;
import com.alkemy.ong.application.service.CategoryService;
import com.alkemy.ong.application.service.CommentService;
import com.alkemy.ong.application.service.ContactService;
import com.alkemy.ong.application.service.MemberService;
import com.alkemy.ong.application.service.NewsService;
import com.alkemy.ong.application.service.OrganizationService;
import com.alkemy.ong.application.service.SlideService;
import com.alkemy.ong.application.service.TestimonialService;
import com.alkemy.ong.application.service.UserService;
import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.ICreateCommentUseCase;
import com.alkemy.ong.application.service.usecase.ICreateContactUseCase;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteNewsUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteTestimonialUseCase;
import com.alkemy.ong.application.service.usecase.IGetCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.application.service.usecase.IListCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.application.service.usecase.ILoginUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateOrganizationUseCase;
import com.alkemy.ong.application.util.ISendEmail;
import com.alkemy.ong.infrastructure.database.repository.CategoryRepository;
import com.alkemy.ong.infrastructure.database.repository.CommentRepository;
import com.alkemy.ong.infrastructure.database.repository.ContactRepository;
import com.alkemy.ong.infrastructure.database.repository.MemberRepository;
import com.alkemy.ong.infrastructure.database.repository.NewsRepository;
import com.alkemy.ong.infrastructure.database.repository.OrganizationRepository;
import com.alkemy.ong.infrastructure.database.repository.SlideRepository;
import com.alkemy.ong.infrastructure.database.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class ServiceBeanConfig {

  @Bean
  public ILoginUseCase loginUseCase(AuthenticationManager authenticationManager,
      UserRepository userRepository) {
    return new AuthenticationService(authenticationManager, userRepository);
  }

  @Bean
  public IDeleteTestimonialUseCase deleteTestimonialUseCase(
      ITestimonialRepository testimonialRepository) {
    return new TestimonialService(testimonialRepository);
  }

  @Bean
  public ICreateUserUseCase createUserUseCase(UserRepository userRepository,
      IOrganizationRepository organizationRepository,
      ISendEmail sendEmail) {
    return new UserService(userRepository, organizationRepository, sendEmail);
  }

  @Bean
  public IUpdateOrganizationUseCase updateOrganizationUseCase(
      OrganizationRepository organizationRepository, SlideRepository slideRepository) {
    return new OrganizationService(organizationRepository, slideRepository);
  }

  @Bean
  public ICreateContactUseCase createContactUseCase(ContactRepository contactRepository) {
    return new ContactService(contactRepository);
  }

  @Bean
  public ICreateCategoryUseCase createCategoryUseCase(CategoryRepository categoryRepository) {
    return new CategoryService(categoryRepository);
  }

  @Bean
  public IDeleteMemberUseCase deleteMemberUseCase(MemberRepository memberRepository) {
    return new MemberService(memberRepository);
  }

  @Bean
  public IDeleteCategoryUseCase deleteCategoryUseCase(ICategoryRepository categoryRepository) {
    return new CategoryService(categoryRepository);
  }

  @Bean
  public IGetOrganizationUseCase getOrganizationUseCase(
      OrganizationRepository organizationRepository, SlideRepository slideRepository) {
    return new OrganizationService(organizationRepository, slideRepository);
  }

  @Bean
  public IDeleteNewsUseCase deleteNewsUseCase(INewsRepository newsRepository) {
    return new NewsService(newsRepository);
  }

  @Bean
  public IListSlideUseCase listSlideUseCase(SlideRepository slideRepository) {
    return new SlideService(slideRepository);
  }

  @Bean
  public IListCategoryUseCase listCategoryUseCase(CategoryRepository categoryRepository) {
    return new CategoryService(categoryRepository);
  }

  @Bean
  public ICreateCommentUseCase createCommentUseCase(CommentRepository commentRepository,
      UserRepository userRepository, NewsRepository newsRepository) {
    return new CommentService(commentRepository, newsRepository, userRepository);
  }

  @Bean
  public IDeleteCommentUseCase deleteCommentUseCase(CommentRepository commentRepository,
                                                    UserRepository userRepository,
                                                    NewsRepository newsRepository) {
    return new CommentService(commentRepository, newsRepository, userRepository);
  }

  @Bean
  public IGetCategoryUseCase getCategoryUseCase(CategoryRepository categoryRepository) {
    return new CategoryService(categoryRepository);
  }

}
