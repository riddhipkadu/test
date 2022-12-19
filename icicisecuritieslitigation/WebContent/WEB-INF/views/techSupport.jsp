<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_container">
<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Support</h2>
					<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
				</div>
			</div><br>
<div class="f_form_content">
        <div class="col-md-12">
           
                <div class="col-md-12">
                    
                    <h2>Contact Us</h2>
                   
                    <div class="col-md-6" id='c_form'>
                        <form  name="contact-form" id="contact-form" method="post" action="./getSupportQuery" onsubmit="return validate()" >
                            <div>
                                <div class="span5">
                                    <label>Full Name</label>
                                    <input type="text" name="Cname" id="Cname" class="form-control" required="required" placeholder="Your Name">
                                    <label>Mobile</label>
                                    <input type="text" name="Cmobile" id="Cmobile" class="form-control" required="required" placeholder="Your Mobile No">
                                    <label>Email Address</label>
                                    <input type="text" name="Cemail" id="Cemail" class="form-control" required="required" placeholder="Your Email Address">
                                </div>
                                <div class="span7">
                                    <label>Query</label>
                                    <textarea name="Cmessage" id="Cmessage"  required="required" class="form-control" rows="8"></textarea>
                                </div>

                            </div>
                            <br/>
                            <button type="submit" name='submit' value="submit" class="btn btn-primary btn-large pull-right" >Send Message</button>                            
                        </form>
                        <br><br>
                       <!-- <div id='Cerr' class="alert alert-danger">  </div>-->
                    </div>
                   
                    <div class="col-md-6">

                        <div class="span3">

                            <h4><strong style="color:#a72f14">Lexcare Global Consultants Pvt. Ltd.</strong> </h4>
                            <p style="color:black;">
                                <i class="fa fa-map-marker" style="font-size:18px;margin-right:10px;"></i>The Capital, 202, B Wing, Baner-Pashan Link Road, Next to Rolling Hill, Baner
Pune - 411045<br>
                            </p>
                            <p style="color:black;">
                                <span class="glyphicon glyphicon-envelope"></span><a href="mailto:support@lexcareglobal.com" style="color: black;"> support@lexcareglobal.com</a>
                            </p>
                            <p style="color:black;">
                                <span class="glyphicon glyphicon-earphone"></span>  020 6719 1111
                            </p>
                            <p style="color:black; text-decoration:underline;">
                                <i class="fa fa-globe" style="font-size:18px;margin-right:10px;"></i><a style="color:black; text-decoration:underline;" href="http://www.lexcareglobal.com">http://www.lexcareglobal.com</a>
                            </p> 
                        </div>
                    </div>
                     <div class="col-md-6" id='c_msg'>

                    </div>
                </div>
         

           
        </div>
			 </div>
</div>

<script>
     function validate(){ 
    	 var fname   = $('#Cname').val();
         var contact = $('#Cmobile').val();
         var mail    = $('#Cemail').val();
         var query   = $("#Cmessage").val();
         //// Validation for name
         if (fname == 0 || fname=="") {
        	 
        	 $( "#Cname" ).attr( "data-placement", "top" );
             $( "#Cname" ).attr( "data-content", "Please enter name..!!" );
             $('#Cname').popover('show');
 				
                return false;
            }
            else
            {
            	$('#Cname').popover('destroy');
            }
         if (!(isNaN(fname))) {
         	
              $( "#Cname" ).attr( "data-placement", "top" );
              $( "#Cname" ).attr( "data-content", "Please enter only characters..!" );
              $('#Cname').popover('show');
              return false;
          }
          else
          {
          	$('#Cname').popover('destroy');
          }
         
         if (!(fname.length>2)) {
         	// $("#oerr").innerHTML="this is invalid name ";
              $( "#Cname" ).attr( "data-placement", "top" );
              $( "#Cname" ).attr( "data-content", "Please enter at least 3 character..!!" );
              $('#Cname').popover('show');
              return false;
          }
          else
          {
          	$('#Cname').popover('destroy');
          }
         
         if (contact == 0 || contact=="") {
           	 
           	 $( "#Cmobile" ).attr( "data-placement", "top" );
                $( "#Cmobile" ).attr( "data-content", "Please enter mobile number..!!" );
                $('#Cmobile').popover('show');
    				
                   return false;
               }
               else
               {
               	$('#Cmobile').popover('destroy');
               }
           
           
           
            if (isNaN(contact)) {
            	
            	 $( "#Cmobile" ).attr( "data-placement", "top" );
                 $( "#Cmobile" ).attr( "data-content", "Please enter only Numbers..!!" );
                 $('#Cmobile').popover('show');
                 return false;
             }
             else
             {
             	$('#Cmobile').popover('destroy');
             }
            
            if (!(contact.length>9)) {
            	// $("#oerr").innerHTML="this is invalid name ";
                 $( "#Cmobile" ).attr( "data-placement", "top" );
                 $( "#Cmobile" ).attr( "data-content", "Please enter valid mobile number..!!" );
                 $('#Cmobile').popover('show');
                 return false;
             }
             else
             {
             	$('#Cmobile').popover('destroy');
             }
////////////////Mail Validation //////////////////
            if (mail === null || mail === "") {
                $("#Cemail").attr("data-placement", "top");
                $("#Cemail").attr("data-content", "Please enter email address..!");
                $('#Cemail').popover('show');

                return false;
            }else
            {
                $('#Cemail').popover('destroy');
            }
            /// validate email format
            var atpos = mail.indexOf("@");
            var dotpos = mail.lastIndexOf(".");
            if (atpos < 1 || dotpos < (atpos + 2) || (dotpos + 2) >= mail.length)
            {
                $("#Cemail").attr("data-placement", "top");
                $("#Cemail").attr("data-content", "Please enter valid email address..!!");
                $('#Cemail').popover('show');
                // alert("Not a valid e-mail address");
                return false;
            } else
            {
                $('#Cemail').popover('destroy');
            }
            
            if (query == 0 || query=="") {
           	 
           	    $( "#Cmessage" ).attr( "data-placement", "top" );
                $( "#Cmessage" ).attr( "data-content", "Please enter query..!!" );
                $('#Cmessage').popover('show');
                   return false;
            }
            else
            {
               	$('#Cmessage').popover('destroy');
            }
            if (!(query.length>2)) {
                  $( "#Cmessage" ).attr( "data-placement", "top" );
                  $( "#Cmessage" ).attr( "data-content", "Please enter at least 3 character..!!" );
                  $('#Cmessage').popover('show');
                  return false;
              }
              else
              {
              	$('#Cmessage').popover('destroy');
              }
     }
</script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

