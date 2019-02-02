export class Code {

  cdGrp      : string; // 코드그룹     
  cdGrpNm    : string; // 코드그룹명    
  cdId       : string; // 코드아이디    
  cdIdNm     : string; // 코드아이디명   
  cd         : string; // 코드       
  cdNm       : string; // 코드명      
  cdDesc     : string; // 코드설명     
  cdTyCd     : string; // 코드유형코드   
  regDtm     : string; // 등록일시     
  regUsrId   : string; // 등록자      
  updDtm     : string; // 변경일시     
  updUsrId   : string; // 변경자      

  comboCodeId : string = "ALL"; // [조회조건] 선택된 콤보 id
  searchValue : string = "";    // [조회조건] 코드관련 

}
