$(document).ready(function(){

    var clientUrl = "http://localhost:8080/client";
    var recordApiUrl = "http://127.0.0.1:5000/record?phrase_time_limit=";

    $("#btnSpeak").click(function(){
      //alert("The paragraph was clicked.");
      console.log("speaking...");
      speakNavigate();
      //speakNavigate2();
    });


    /*
    function speakNavigate2(){
        $.ajax({
             type:"GET",
             url: recordApiUrl+"3",//3 seconds
             success:function(data){
                disableButton($("#btnSpeak"), false);

                debugger;
                data = data.result;
                //debugger;
                console.log("you speak..."+data);

                interpretCommand(data);
            },
        });
    }

    function interpretCommand(text) {
        $.ajax({
            url: clientUrl+'/interpret-command',
            method: 'GET',
            data: { command: text },
            success: function(response) {
                executeCommand(response);
            },
            error: function() {
                alert('Error interpreting command');
            }
        });
    }

    function executeCommand(response) {
        const command = JSON.parse(response);
        if (command.action === 'navigate') {
            window.location.href = command.url;
        } else if (command.action === 'search') {
            window.location.href = `/search?query=${encodeURIComponent(command.query)}`;
        } else {
            alert('Command not recognized');
        }
    }
    */

    function speakNavigate(){
      disableButton($("#btnSpeak"), true);
      //debugger;
      $.ajax({
             type:"GET",
             url: recordApiUrl+"3",//3 seconds
             success:function(data){
                disableButton($("#btnSpeak"), false);

                data = data.result;
                //debugger;
                console.log("you speak..."+data);

                //console.log("checking....");
                getActionUrl(data, function(found, endpoint){
                    console.log('done...'+data + ", found..."+found+", endpoint..."+endpoint);
                    if(found){
                        window.location.replace(clientUrl+endpoint);
                    }else {
                        alert("Sorry i don't find..."+data);
                    }
                });
            },
            error:function(data){
                disableButton($("#btnSpeak"), false);
                alert('There were any error while calling speech text api. contact support.'+data.error)
            }
        });
    }

    function getActionUrl(speechText, callback){
        var found=false;
        var redirect="";

        //View cart
        var homeArray = ["home", "home page", "main page"];
        redirect = "/";
        found = viewAction(homeArray, speechText);
        if(found) return callback(found, redirect);

        //Part search
        var partSearchArray = ["part search", "search", "search part"];
        redirect = "/part-search";
        found = viewAction(partSearchArray, speechText);
        if(found) return callback(found, redirect);

        //Part search
        var categoryArray = ["categories", "category"];
        redirect = "/category";
        found = viewAction(categoryArray, speechText);
        if(found) return callback(found, redirect);

        //---------Pages ---------//
        //About us
        var aboutUsArray = ["pages", "page", "about us", "about"];
        redirect = "/about-us";
        found = viewAction(aboutUsArray, speechText);
        if(found) return callback(found, redirect);

        //Contact us
        var contactUsArray = ["contact us", "contact"];
        redirect = "/contact-us";
        found = viewAction(contactUsArray, speechText);
        if(found) return callback(found, redirect);

        //FAQ
        var faqArray = ["FAQ", "faq", "frequently ask" ,"frequently ask question", "frequently asked question"];
        redirect = "/faq";
        found = viewAction(faqArray, speechText);
        if(found) return callback(found, redirect);

        //Privacy policy
        var privacyPolicyArray = ["privacy policy", "privacy"];
        redirect = "/privacy-policy";
        found = viewAction(privacyPolicyArray, speechText);
        if(found) return callback(found, redirect);

        //Return policy
        var returnPolicyArray = ["return policy", "return"];
        redirect = "/return-policy";
        found = viewAction(returnPolicyArray, speechText);
        if(found) return callback(found, redirect);


        //Terms and conditions
        var termsAndConditionsArray = ["terms and conditions", "term and condition", "term and conditions", "terms and condition"];
        redirect = "/terms-and-conditions";
        found = viewAction(termsAndConditionsArray, speechText);
        if(found) return callback(found, redirect);

        //View cart
        var viewCartArray = ["view card", "view cart"];
        redirect = "/view-cart";
        found = viewAction(viewCartArray, speechText);
        if(found) return callback(found, redirect);

        //Checkout
        var checkoutArray = ["checkout", "check out"];
        redirect = "/checkout";
        found = viewAction(checkoutArray, speechText);
        if(found) return callback(found, redirect);

        //My account
        var myAccountArray = ["my account", "account"];
        redirect = "/my-account";
        found = viewAction(myAccountArray, speechText);
        if(found) return callback(found, redirect);

        //Order history
        var orderHistoryArray = ["order history", "history"];
        redirect = "/order-history";
        found = viewAction(orderHistoryArray, speechText);
        if(found) return callback(found, redirect);

        //Change password
        var changePasswordArray = ["change password", "password"];
        redirect = "/change-password";
        found = viewAction(changePasswordArray, speechText, '/change-password');
        if(found) return callback(found, redirect);

        //Login
        var loginArray = ["login", "sign in"];
        redirect = "/login";
        found = viewAction(loginArray, speechText, '/login');
        if(found) return callback(found, redirect);

        //Logout
        var logoutArray = ["logout"];
        redirect = "/logout";
        found = viewAction(logoutArray, speechText);
        if(found) return callback(found, redirect);

        //Register
        var registerArray = ["register", "sign up"];
        redirect = "/register";
        found = viewAction(registerArray, speechText, '/register');
        if(found) return callback(found, redirect);

        callback(false, '');
    }

    function viewAction(array, speechText){
        //debugger;
        const contains = array.some(element => speechText.includes(element));
        if(contains){
            console.log("redirecting to..."+endpoint)
            //window.location.replace(clientUrl+endpoint);
            return true;
        }
        return false;
    }

    function disableButton(element, isEnable){
        element.prop("disabled", isEnable);
    }

    var partSearch = clientUrl+"/part-search";


    $("#btnSpeakSearch").click(function(){
        console.log("speaking...");
        speakSearch();
    });

    function getMakes(){
         var makes = ['Toyota', 'Honda', 'Ford', 'Mercedes-Benz', 'BMW', 'Hyundai', 'Nissan', 'Chevrolet', 'Volkswagen', 'Audi', 'Kia', 'Mazda', 'Subaru', 'Lexus', 'Jaguar', 'Acura', 'GMC', 'Kawasaky', 'Mercedes', 'Yamaha'];
         return makes;
    }
    function getCategories(){
        var categories = ['Brake Pads','Oil Filter', 'Air Filter','Radiator','Timing Belt','Alternator','Spark Plug',
                          'Battery','Headlight', 'Fuel Pump','Water Pump','Brake Disc','Windshield Wiper','Exhaust Pipe',
                          'Tire','Lighting','Oil Fluids','Tools & Equipment', 'Wheels & Tires','Car Parts','Body Interior', 'Body Exterior','Engine'];

        return categories;
    }
    function getYears(){
        var years = ['1999', '2000', '2001', '2002', '2003', '2004', '2005', '2006', '2007', '2008', '2009', '2010'
        , '2011', '2012', '2013', '2014', '2015', '2016', '2017', '2018', '2019', '2020'
        , '2021', '2022', '2023', '2024', '2025'
        ];
        return years;
    }

    function matchingElement(array, speechText){
        if (Array.isArray(array)) {
            //debugger;
            const matchingElement = array.find(element =>
              typeof element === 'string' && speechText.toLowerCase().includes(element.toLowerCase())
            );

            if(matchingElement){
                return matchingElement;
            }
            return "";
        }else {
              console.log('The variable array is not an array or is undefined');
        }
    }

    function speakSearch(){
        disableButton($("#btnSpeakSearch"), true);
        // Make the call to the record API
        $.ajax({
            type: "GET",
            url: recordApiUrl+"3",
            success: function(data){
                disableButton($("#btnSpeakSearch"), false);

                var keyword = data.result;
                console.log("you speak... " + keyword);

                debugger;

                var years = getYears();
                //fetching year
                var yearVal = matchingElement(years, keyword);
                console.log("yearVal="+yearVal);

                var makes = getMakes();
                //fetching make

                var makeVal = matchingElement(makes, keyword);
                console.log("makeVal="+makeVal);

                var categories = getCategories();
                //fetching category
                var categoryVal = matchingElement(categories, keyword);
                console.log("categoryVal="+categoryVal);

                //extract year, make and category from keyword
                //keyword=audi 2005 battery
                //make=audi
                //year=2005
                //category=battery

                // Construct the full URL with query parameters
                const params = new URLSearchParams({
                    name: keyword, // Use the data received from record API
                    make: makeVal,
                    model: '',
                    year: yearVal,
                    category: categoryVal,
                    page: '1',
                    size: '100'
                });
                const url = `${partSearch}?${params.toString()}`;

                // Redirect to the constructed URL
                window.location.href = url;
            },
            error: function(data){
                disableButton($("#btnSpeakSearch"), false);
                alert('There was an error while calling the speech text API. Contact support. ' + data.error);
            }
        });
    }
});