function sendCurrentPage(object){
    $.get('controller', encodeURI('action=PRELOAD_WELCOMEPAGE&page='+object.value),function (){
        location.reload()
    });
}