package org.sopt.and.data.common


//API 메서드와 엔드포인트에 따라 응답 코드가 다르므로 어느 호출인지 식별하기 위한 객체
object APICallType {
    const val REGISTER_USER = "registerUser"
    const val LOGIN_USER = "loginUser"
    const val GET_MY_HOBBY = "getMyHobby"
}