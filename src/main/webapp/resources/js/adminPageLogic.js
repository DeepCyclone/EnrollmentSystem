
window.onload = function () {
        jQuery.ajaxSetup({async: false});
}


function parseAndSendUserStatus(object){
        let name = object.name
        let value = object.value;
        let userId_Faculty_Pair = name.split(":");
        let educationForm_EnrollmentStatus_pair = value.split(":");
        let userId = userId_Faculty_Pair[0];
        let faculty = userId_Faculty_Pair[1];
        let educationForm = educationForm_EnrollmentStatus_pair[0];
        let enrollmentStatus = educationForm_EnrollmentStatus_pair[1];
        console.log(enrollmentStatus);
        changeUserStatus(userId,faculty,educationForm,enrollmentStatus)
}

function changeUserStatus(userId,faculty,educationForm,status){
        $.post('controller', {action:"UPDATE_ENROLLMENT_STATUS",userId:userId,faculty:faculty,educationForm:educationForm,enrollmentStatus:status},function (){
                console.log("success");
        });
        window.location.reload();
}

function preloadUserPopup(userID) {
        let popupMarks = document.getElementsByClassName('popupData');
        let marksTable = document.getElementById("popupMarks").querySelector('tbody');
        if(popupMarks.length === 0) {
        $.get('controller', {action: "PRELOAD_USER_POPUP", userID: userID},"application/json")
            .done(function (json) {
                            for (let id in json) {
                                    let row = document.createElement('tr');
                                    row.className = "popupData";
                                    row.appendChild(document.createElement('td')).appendChild(document.createTextNode(json[id]['name']));
                                    row.appendChild(document.createElement('td')).appendChild(document.createTextNode(json[id]['value']));
                                    marksTable.appendChild(row);
                            }

                    })
            }
}
function clearPopup(){
        let pops = document.getElementsByClassName('popupData');
        for(let ind in pops){
                pops.item(Number.parseInt(ind)).remove();
        }

}
