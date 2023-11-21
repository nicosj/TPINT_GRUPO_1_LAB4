$(document).ready(function(){
    // Activate tooltip
    sessionStorage.getItem('currentClass')==="true"?$('.navbar-primary').addClass('collapsed'):$('.navbar-primary').removeClass('collapsed');

    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function(){
        if(this.checked){
            checkbox.each(function(){
                this.checked = true;
            });
        } else{
            checkbox.each(function(){
                this.checked = false;
            });
        }
    });
    checkbox.click(function(){
        if(!this.checked){
            $("#selectAll").prop("checked", false);
        }
    });

    $('.btn-expand-collapse').on('click',function(e) {
        $('.navbar-primary').toggleClass('collapsed');
        if(sessionStorage.getItem('currentClass')==="true"){
            sessionStorage.setItem('currentClass', "false");
        }else {
            sessionStorage.setItem('currentClass', "true");
        }


    });


});


