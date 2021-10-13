const facSubMap = new Map();//сюда подгружается соответствия факультет-предметы из БД
const set = new Set();//эта коллекция заполняется текущими факультетами с заполненными значениями баллов
const facFormsMap = new Map;//факультет-формы обучения
const facilities = new Set();

window.onload = function (){
    jQuery.ajaxSetup({async:false});
    preloadFacultiesMap();
    preloadFacultiesAndForms();
    marksSetter();
    facultiesAndFormsSetter();
    facilitiesStatusPreloader();
    facultiesAndFormsSetter();
}

function facilitiesStatusPreloader(){
    $.get('controller', encodeURI('action=PRELOAD_FACILITIES_TAB'), function (data) {
        let values = [];
        for(let key in data){
            values = [];
            for(let value in data[key]){
                values.push(data[key][value])
            }
            facSubMap.set(key,values);
        }
    }, "json");
}

function facilitiesStatusSetter(){

}

function preloadFacultiesMap(){
    $.get('controller', encodeURI('action=PRELOAD_FACULTIES_PAGE'), function (data) {
        let values = [];
        for(let key in data){
            values = [];
            for(let value in data[key]){
                values.push(data[key][value])
            }
            facSubMap.set(key,values);
        }
    }, "json");
}

function preloadFacultiesAndForms(){
    $.get('controller', encodeURI('action=PRELOAD_SELECTED_FACULTIES'), function (data) {
        let values = [];
        console.log(data);
        for(let index in data){
            let key;
            key = data[index]['facultyName'];
            values = data[index]['educationForms'];
            facFormsMap.set(key,values);
        }
    }, "json");
}

function facultiesAndFormsSetter(){
    facFormsMap.forEach(function (value, key){
        console.log(key)
        document.getElementById('Faculty:' + key).checked = true;
        for (let index in value) {
            console.log(value[index])
            if (value[index] === 'budget') {
                document.getElementById('budgCheckbox:' + key).checked = true;
            }
            if (value[index] === 'paid') {
                document.getElementById('paidCheckbox:' + key).checked = true;
            }
        }
    })
}

function marksSetter(){
    let marks = document.getElementsByClassName("mark-cell")//парсим то, что уже заполнено после загрузки из БД
    for (let x in marks) {
        let element = {};
        element.id = marks[x]['id'];
        element.value = marks[x]['value'];
        marksSelectorListener(element);
    }
}
function marksSelectorListener(element) {
    if (element.value > 0 && element.value <= 10) {
        if (!set.has(element.id)) {//3 предмета + аттестат
            set.add(element.id);
        }

        if (set.size >= 3) {//разделить аттестат) set.size === 4
            document.getElementById('faculties-selector').innerHTML = ''
            for (const [key, value] of facSubMap) {
                let inputCode;
                let labelCode;
                let budgButton;
                let paidButton;
                let comparer = true;
                console.log("iter")
                console.log(value.length)
                    for(const i of value){
                        if(!set.has(i)){
                            console.log(i + "===");
                            for(const v of set){
                                console.log("set el" + v);
                            }
                            console.log("false")
                            comparer = false;
                        }
                }
                console.log("true")
                if (comparer === true) {
                    inputCode = "<td><input type = 'checkbox' name = Faculty:" + key + " value = " + key + " id = 'Faculty:" + key + "'>";
                    labelCode = "<label for=" + key + ">" + key + "</label></td>";
                    budgButton = "<td><div class='form-check form-switch'>" +
                        "<input class='form-check-input' name = 'e_form:" + key + "' value = 'budget' type='checkbox' id = 'budgCheckbox:" + key + "'>" +
                        "<label class='form-check-label' for='budgCheckbox'>Budget</label></div></td>";
                    paidButton = "<td><div class='form-check form-switch'>" +
                        "<input class='form-check-input' name = 'e_form:" + key + "' value = 'paid' type='checkbox' id = 'paidCheckbox:" + key + "'>" +
                        "<label class='form-check-label' for='paidCheckbox'>Paid</label></div></td>";
                    let formCode = "<tr>" + inputCode + labelCode + budgButton + paidButton + "</tr>";
                    if ($('#faculties-selector').find('tbody').length === 0) {
                        $('#faculties-selector').append('<tbody></tbody>');
                    }
                    $('#faculties-selector').find('tbody').append(formCode);//jquery тут проще. Ну и вообще он крут
                }
            }
        }
    }
    else{
        if (set.has(element.id)) {
            set.delete(element.id);
        }
        if (set.size < 3) {
            document.getElementById('faculties-selector').innerHTML = '';
        }//переделать rerenderer
    }


}