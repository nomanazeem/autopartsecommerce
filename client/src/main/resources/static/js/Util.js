 function getModels(url) {
    var make = $(".make").val();
    //var url = /*[[@{/models}]]*/
    $.get(url+"?make=" + make, function( data ) {
        $(".model").empty();
        data.forEach(function(item, i) {
            var option = "<option value = " + item.id + ">" + item.name +  "</option>";
            $(".model").append(option);
        });
    });
};