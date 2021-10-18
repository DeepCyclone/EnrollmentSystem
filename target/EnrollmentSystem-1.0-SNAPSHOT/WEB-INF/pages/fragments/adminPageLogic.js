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
}