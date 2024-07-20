$(document).ready(function(){

    var clientUrl = "http://localhost:8080/client";

    $("#btnSpeak").click(function(){
      //alert("The paragraph was clicked.");
      console.log("speaking...");

      //debugger;
      $.ajax({
             type:"GET",
             url: clientUrl+"/speech-to-text",
             success:function(data){
                //debugger;
                console.log("you speak..."+data);
                if (data.indexOf("Google Speech Recognition") >= 0){
                    alert(data);
                    return;
                }

                console.log("checking....");
                var found = getActionUrl(data, function(){
                    console.log('found is...'+found);
                    if(!found)
                        alert("Sorry i don't find..."+data);
                });
            },
            error:function(data){
                alert('There were any error while calling speech text api. contact support.')
            }
         });
    });

    function getActionUrl(speechText){
        var found=false;
        //View cart
        var homeArray = ["home", "home page", "main page"];
        found = viewAction(homeArray, speechText, '/');

        //Part search
        var partSearchArray = ["part search", "search", "search part"];
        found = viewAction(partSearchArray, speechText, '/part-search');

        //Part search
        var categoryArray = ["categories", "category"];
        found = viewAction(categoryArray, speechText, '/category');

        //---------Pages ---------//
        //About us
        var aboutUsArray = ["pages", "about us", "about"];
        found = viewAction(aboutUsArray, speechText, '/about-us');

        //Contact us
        var contactUsArray = ["contact us", "contact"];
        found = viewAction(contactUsArray, speechText, '/contact-us');

        //FAQ
        var faqArray = ["FAQ", "faq", "frequently ask" ,"frequently ask question", "frequently asked question"];
        found = viewAction(faqArray, speechText, '/faq');


        //Privacy policy
        var privacyPolicyArray = ["privacy policy", "privacy"];
        found = viewAction(privacyPolicyArray, speechText, '/privacy-policy');

        //Return policy
        var returnPolicyArray = ["return policy", "return"];
        found = viewAction(returnPolicyArray, speechText, '/return-policy');

        //Terms and conditions
        var termsAndConditionsArray = ["terms and conditions", "term and condition", "term and conditions", "terms and condition"];
        found = viewAction(termsAndConditionsArray, speechText, '/terms-and-conditions');

        //View cart
        var viewCartArray = ["view card", "view cart"];
        found = viewAction(viewCartArray, speechText, '/view-cart');

        //Checkout
        var checkoutArray = ["checkout", "check out"];
        found = viewAction(checkoutArray, speechText, '/checkout');

        //My account
        var myAccountArray = ["my account", "account"];
        found = viewAction(myAccountArray, speechText, '/my-account');

        //Order history
        var orderHistoryArray = ["order history", "history"];
        found = viewAction(orderHistoryArray, speechText, '/order-history');

        //Change password
        var changePasswordArray = ["change password", "password"];
        found = viewAction(changePasswordArray, speechText, '/change-password');

        //Login
        var loginArray = ["login", "sign in"];
        found = viewAction(loginArray, speechText, '/login');

        //Logout
        var logoutArray = ["logout"];
        found = viewAction(logoutArray, speechText, '/logout');

        //Register
        var registerArray = ["register", "sign up"];
        found = viewAction(registerArray, speechText, '/register');

        return found;
    }

    function viewAction(array, speechText, endpoint){
        //debugger;
        const contains = array.some(element => speechText.includes(element));
        if(contains){
            console.log("redirecting to..."+endpoint)
            window.location.replace(clientUrl+endpoint);
            return true;
        }
        return false;
    }



	/* ---------------------------------------------------
	Preloading Screen
-------------------------------------------------- */
//    $(window).load(function() {
//        // Animate loader off screen
//        //$('body').addClass('loaded');
//        alert('Home');
//    });
});
