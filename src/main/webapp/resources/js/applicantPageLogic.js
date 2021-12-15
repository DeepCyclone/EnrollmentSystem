const facSubMap = new Map();//сюда подгружается соответствия факультет-предметы из БД
const set = new Set();//эта коллекция заполняется текущими факультетами с заполненными значениями баллов
const facFormsMap = new Map;//факультет-формы обучения
const facilities = new Set();
let maxPriority;

class PrioritiesHolder{
    constructor(facultyName,educationForm,priority) {
        this.facultyName = facultyName;
        this.educationForm = educationForm;
        this.priority = priority;
    }

}

window.onload = function (){
    jQuery.ajaxSetup({async:false});
    preloadFacultiesMap();
    preloadFacultiesAndForms();
    facilitiesStatusPreloader();
    marksSetter();
    facultiesAndFormsSetter();
    facilitiesStatusSetter();
    setMaxPriority();
}

function facilitiesStatusPreloader(){
    $.get('controller', encodeURI('action=PRELOAD_FACILITIES_TAB'), function (data) {
        for(let value of data){
           facilities.add(value);
        }
    }, "json");
}

function preloadPriorities(){
    $.get('controller', encodeURI('action=PRELOAD_PRIORITIES'), function (data) {
        for(let value of data){
            facilities.add(value);
        }
    }, "json");
}

function facilitiesStatusSetter(){// можно с помощью c:if и передачи каких-то флажков на страницу, но уже так
    if(facilities.has('Gold Medal')){
        document.getElementById('goldmedalFacility').checked = true;
    }
    if(facilities.has('Orphan')){
        document.getElementById('orphanFacility').checked = true;
    }
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
        let values;
        for(let index in data){
            let key;
            key = data[index]['facultyName'];
            values = data[index]['educationFormStatuses'];
            if(facFormsMap.has(key)){
                let oldVal = facFormsMap.get(key);
                let val = [];
                val.push(oldVal);
                val.push(values);
                facFormsMap.set(key,val);
            }
            else {
                facFormsMap.set(key, values);
            }
        }
    }, "json");
}

function facultiesAndFormsSetter(){
    facFormsMap.forEach(function (value, key){
        document.getElementById('Faculty:' + key).checked = true;
        for (let ind in value) {
            if(typeof value[ind] === "object"){
                for(let a in value[ind]){
                    checkboxSetter(key,a);
                }
            }
            else {
                checkboxSetter(key,ind);
            }
        }
    })
}

function checkboxSetter(key,value){
    if (value === "\u0411\u044e\u0434\u0436\u0435\u0442\u043d\u0430\u044f") {
        document.getElementById('budgCheckbox:' + key).checked = true;
    }
    if (value === "\u041f\u043b\u0430\u0442\u043d\u0430\u044f") {
        document.getElementById('paidCheckbox:' + key).checked = true;
    }
}

function marksSetter(){
    let marks = document.getElementsByClassName("mark-cell")
    for (let x in marks) {
        let element = {};
        element.id = marks[x]['id'];
        element.value = marks[x]['value'];
        marksSelectorListener(element);
    }
}

function marksSelectorListener(element) {
    if ((element.id === "schoolCertificate" && element.value > 4 && element.value <= 10) ||
        (element.id !== "schoolCertificate" && element.value > 7 && element.value <= 100)) {
        if (!set.has(element.id)) {
            set.add(element.id);
        }

        if (set.size >= 4 && isCertificateAvailable(set)) {
            buildAvailableFacultiesList();
            facultiesAndFormsSetter();
        }
    } else {
        if (set.has(element.id)) {
            set.delete(element.id);
        }
        document.getElementById('faculties-selector').innerHTML = '';
        buildAvailableFacultiesList();
    }
    setMaxPriority();
}

function isCertificateAvailable(elementsSet){
    for(let element of elementsSet){
        if(element === "schoolCertificate"){
            return true;
        }
    }
}

function priorityChecker(element){
    fieldValueBoundsChecker(element,maxPriority,1);
}

function testsChecker(element){
    fieldValueBoundsChecker(element,100,7)
}

function certificateChecker(element){
    fieldValueBoundsChecker(element,10,4)
}

function fieldValueBoundsChecker(element,upperBound,lowerBound){

    if(!/^[0-9]+$/.test(element.value) || element.value > upperBound || element.value < lowerBound){
        element.value = "";
    }
}

function setMaxPriority(){
    maxPriority = $('#faculties-selector').find('tbody tr').length;
}

function buildAvailableFacultiesList(){
    document.getElementById('faculties-selector').innerHTML = ''
    for (const [key, value] of facSubMap) {
        let inputCode;
        let labelCode;
        let budgButton;
        let paidButton;
        let priorityCodeBudg;
        let priorityCodePaid;
        let comparer = true;
        for (const i of value) {
            if (!set.has(i)) {
                comparer = false;
                break;
            }
        }
        if (comparer === true) {
            inputCode = "<td><input type = 'checkbox' name = Faculty:" + key + " value = " + key + " id = 'Faculty:" + key + "'>";
            priorityCodeBudg = "<td><input type = 'text' name = 'Priority:\u0411\u044e\u0434\u0436\u0435\u0442\u043d\u0430\u044f:" + key + "' size='1' onkeyup='priorityChecker(this)'></td>"
            priorityCodePaid = "<td><input type = 'text' name = 'Priority:\u041f\u043b\u0430\u0442\u043d\u0430\u044f:" + key + "' size='1' onkeyup='priorityChecker(this)'></td>"
            labelCode = "<label for=" + key + ">" + key + "</label></td>";
            budgButton = "<td><div class='form-check form-switch'>" +
                "<input class='form-check-input' name = 'e_form:" + key + "' value = '\u0411\u044e\u0434\u0436\u0435\u0442\u043d\u0430\u044f' type='checkbox' id = 'budgCheckbox:" + key + "'>" +
                "<label class='form-check-label' for='budgCheckbox'>Budget</label></div></td>";
            paidButton = "<td><div class='form-check form-switch'>" +
                "<input class='form-check-input' name = 'e_form:" + key + "' value = '\u041f\u043b\u0430\u0442\u043d\u0430\u044f' type='checkbox' id = 'paidCheckbox:" + key + "'>" +
                "<label class='form-check-label' for='paidCheckbox'>Paid</label></div></td>";
            let formCode = "<tr>" + inputCode + labelCode + budgButton + priorityCodeBudg +  paidButton + priorityCodePaid + "</tr>";
            if ($('#faculties-selector').find('tbody').length === 0) {
                $('#faculties-selector').append('<tbody></tbody>');
            }
            $('#faculties-selector').find('tbody').append(formCode);

        }
    }
}
