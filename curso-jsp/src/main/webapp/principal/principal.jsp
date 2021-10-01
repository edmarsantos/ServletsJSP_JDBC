<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">


<!-- include da Head -->
<jsp:include page="head.jsp"></jsp:include>

  <body>
<!-- Pre-loader include do tema escolhido-->
<jsp:include page="theme-loader.jsp"></jsp:include>
<!-- Pre-loader end -->


  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          
 <!-- include da barra de navegação -->         
<jsp:include page="navbar.jsp"></jsp:include>


          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
<!-- include do menu -->
<jsp:include page="navbarmainmenu.jsp"></jsp:include>

                  <div class="pcoded-content">
                    
                      <!-- include Page-header start Cabeçalho da pagina -->
                     <jsp:include page="page-header.jsp"></jsp:include>
                       <!-- Page-header end  Cabeçalho da pagina -->
                       
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <div class="row">
                                          
                                         <h1>Conteudo do Sistema</h1>
                                          
                                        </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  
    
    <!-- include do javascript Jquery -->
    <jsp:include page="javascriptfile.jsp"></jsp:include>
  
  
</body>

</html>
