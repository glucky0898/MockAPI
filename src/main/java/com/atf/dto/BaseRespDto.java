package com.atf.dto;


public class BaseRespDto extends BaseDto {
    private static final long serialVersionUID = 4970571425853582113L;

    /**
     * @Fields respCode : 响应码
     */
    protected String respCode;
    /**
     * @Fields respMsg : 响应信息
     */
    protected String respMsg;

    public BaseRespDto(){
    }

    public BaseRespDto(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    public static BaseRespDto success(String respMsg) {
        return new BaseRespDto(Constant.SUCCESS, respMsg);
    }

    /**
     *
     *@return 获取{@link #respCode}
     */
    public String getRespCode() {
        if(respCode == null || "0000".equals(respCode) || respCode.length() != 4){
            return respCode;
        }else{
            return CommonConstants.APP_CODE + respCode;
        }

    }
    /**
     *
     *@param respCode 设置{@link #respCode}
     */
    public void setRespCode(String respCode) {
        if(respCode == null || "0000".equals(respCode) || respCode.length() != 4){
            this.respCode = respCode;
        }else{
            this.respCode = CommonConstants.APP_CODE + respCode;
        }
    }
    /**
     *
     *@return 获取{@link #respMsg}
     */
    public String getRespMsg() {
        return respMsg;
    }
    /**
     *
     *@param respMsg 设置{@link #respMsg}
     */
    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    @Override
    public String toString() {
        return "BaseResDTO [respCode=" + respCode + ", respMsg=" + respMsg + "]";
    }
}
