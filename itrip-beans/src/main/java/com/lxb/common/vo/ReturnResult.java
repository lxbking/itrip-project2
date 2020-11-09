package com.lxb.common.vo;

import com.lxb.common.constants.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一的响应对象
 */
@Data
@ApiModel(description = "统一返回实体类")
public class ReturnResult implements Serializable {


    private static final long serialVersionUID = 2591581492906026235L;

   @ApiModelProperty(value = "是否成功，true：成功，false：失败")
   private Boolean success;

   @ApiModelProperty(value = "异常ma")
   private String errorCode;

   @ApiModelProperty(value = "响应提示信息")
   private String msg;

   @ApiModelProperty(value = "返回数据")
   private Object data;

   public ReturnResult(Boolean success, String errorCode, String msg, Object data) {
        this.success = success;
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
   }

    /**
     * 操作成功，指定返回信息和数据
     * @param msg 返回信息
     * @param data  指定数据
     * @return
     */
   public static ReturnResult ok(String msg,Object data){
       return new ReturnResult(true, ErrorCodeEnum.OK.getErrorCode(), msg, data);
   }

    /**
     * 操作成功，指定响应数据
     * @param data  指定数据
     * @return
     */
   public static ReturnResult ok(Object data){
       return ReturnResult.ok(ErrorCodeEnum.OK.getErrorCode(),data);
   }


    /**
     * 操作成功，返回错误码
     * @param errorCodeEnum 指定错误码
     * @return
     */
   public static ReturnResult ok(ErrorCodeEnum errorCodeEnum){
       return ok(errorCodeEnum,null);
   }


    /**
     * 操作成功，无响应数据
     * @return
     */
    public static ReturnResult ok(){
        return ReturnResult.ok(ErrorCodeEnum.OK.getErrorCode(),null);
    }

    /**
     * 操作成功
     * @param errorCodeEnum 错误码枚举
     * @param data  数据
     * @return
     */
    public static ReturnResult ok(ErrorCodeEnum errorCodeEnum,Object data){
        return new ReturnResult(true, errorCodeEnum.getErrorCode(), errorCodeEnum.getMsg(), data);
    }

    /////////////////////////////////////////////////////////////////////////////////

    /**
     * 异常响应
     * @param success    是否成功
     * @param errorCode 内部错误码
     * @param msg   错误信息
     * @param data  指定数据
     * @return
     */
    public static ReturnResult error(Boolean success,String errorCode,String msg,Object data){
        return new ReturnResult(success, errorCode, msg, data);
    }


    /**
     * 异常响应，无数据，返回错误信息
     * @param success   是否成功
     * @param errorCode 内部错误码
     * @param msg   错误信息
     * @return
     */
    public static ReturnResult error(Boolean success,String errorCode,String msg){
        return error(false,errorCode,msg,null);
    }


    /**
     * 异常响应，无数据
     * @param errorCode 内部错误码
     * @param msg   错误信息
     * @return
     */
    public static ReturnResult error(String errorCode,String msg){
        return error(false, errorCode, msg);
    }


    /**
     * 异常响应，无数据
     * @param errorCodeEnum 错误码枚举
     * @return
     */
    public static ReturnResult error(ErrorCodeEnum errorCodeEnum){
        return error(errorCodeEnum.getErrorCode(), errorCodeEnum.getMsg());
    }


    /**
     * 异常响应，无数据（系统执行出错）
     * @return
     */
    public static ReturnResult error(){
        return error(ErrorCodeEnum.SYSTEM_EXECUTION_ERROR);
    }
}
