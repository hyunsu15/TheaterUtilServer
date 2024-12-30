package com.example.theatherUtil.calendar.domain;

import com.example.theatherUtil.common.exception.BaseException;

public record SignIn(String id, String password, SIGN_TYPE signType) {
  public SignIn(String id,String password,String type){
    this(id,password,getMatchType(type));
  }

 private static SIGN_TYPE getMatchType(String type){
  return switch (type.toLowerCase()){
     case "cgv" ->  SIGN_TYPE.CGV;
     case "megabox"->SIGN_TYPE.MEGABOX;
     case "lotte" -> SIGN_TYPE.LOTTE_CINEMA;
     case "lottecinema"->SIGN_TYPE.LOTTE_CINEMA;
     default ->   throw new BaseException("지원되지 않은 극장");
   };
 }

  public boolean isNotSignInType(SIGN_TYPE signType){
   return this.signType!=signType;
 }
}
