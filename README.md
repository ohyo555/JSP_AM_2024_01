## jsp를 이용한 회원 + 게시글 프로젝트

#### JSP
- 서블릿의 단점을 보완하고자 만든 서블릿 기반의 스크립트 기술(서버 스크립트 기술)
- HTML 내부에 Java 코드를 삽입하는 형식
![JSP의동작구조](https://github.com/ohyo555/JSP_AM_2024_01/assets/153146836/83a7dad7-e570-4402-9c42-ae7ece29bf50)

<br>

#### Dispatcher-Servlet
- HTTP 프로토콜로 들어오는 모든 요청을 가장 먼저 받아 적합한 컨트롤러에 위임해주는 프론트 컨트롤러(Front Controller)
- ✅ Front Controller: 주로 서블릿 컨테이너의 제일 앞에서 서버로 들어오는 클라이언트의 모든 요청을 받아서 처리해주는 컨트롤러
![image](https://github.com/ohyo555/JSP_AM_2024_01/assets/153146836/fb042111-a96c-4db9-a6c6-f12a3e5dce27)

#### 구조
- Dispatcher를 통해 http로 요청 보낼때 (controll, service, db 등등 작업을 요청)해서 jsp로 출력
![dispatcher1](https://github.com/ohyo555/JSP_AM_2024_01/assets/153146836/381c6c8a-a571-45b3-9cbe-a33201e78957)
![dispatcher2](https://github.com/ohyo555/JSP_AM_2024_01/assets/153146836/4c08a9e5-8795-41db-92cc-67f034749a0c)

- 메인페이지
  ![jsp메인](https://github.com/ohyo555/JSP_AM_2024_01/assets/153146836/cde97a20-8e9b-48f7-80f8-517ed043855f)

- 게시글 리스트
![jsp게시글](https://github.com/ohyo555/JSP_AM_2024_01/assets/153146836/141fd50b-d7ea-4eb3-8a7b-afebdf6742d4)
  ```
  // DispatcherServlet
  if (controllerName.equals("article")) {
      ArticleController articleController = new ArticleController(request, response, conn);

      if (actionMethodName.equals("list")) {
        articleController.showList();
      }
    }
  
  // article controller
  public void showList() throws ServletException, IOException {
  		int page = 1;
  
  		if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
  			page = Integer.parseInt(request.getParameter("page"));
  		}
  
  		int itemsInAPage = articleService.getItemsInAPage();
  
  		int totalPage = articleService.getTotalPage();
  
  		List<Article> articles = articleService.getForPrintArticles(page);
  
  		request.setAttribute("page", page);
  		request.setAttribute("totalPage", totalPage);
  		request.setAttribute("itemsInAPage", itemsInAPage);
  		request.setAttribute("articles", articles);
  
  		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
  	}
  ```
- 로그인 검증
![로그인 검증](https://github.com/ohyo555/JSP_AM_2024_01/assets/153146836/b91a9861-8a53-416f-9fcc-ebc5de9696f9)

- 인증 후 글쓰기
![jsp글쓰기](https://github.com/ohyo555/JSP_AM_2024_01/assets/153146836/70114123-70d9-41bd-9772-83dba3f15376)
