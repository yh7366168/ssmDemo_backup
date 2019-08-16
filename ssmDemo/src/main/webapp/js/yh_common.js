/*校验*/
function isNullAndConfirm(field, message) {
    if(field==null || field=="" || field==undefined){
        confirm(message);
        return false;
    }
}
/*非空校验*/
function isNull(field) {
    if(field==null || field=="" || field==undefined){
        return true
    }else{
        return false;
    }
}

/**
 * 角色按钮
 * */


