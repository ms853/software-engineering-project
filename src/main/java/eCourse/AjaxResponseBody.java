package ecourse;

import com.fasterxml.jackson.annotation.JsonView;

import ecourse.domain.Views;

public class AjaxResponseBody {

  @JsonView(Views.Public.class)
  String msg;

  @JsonView(Views.Public.class)
  String code;

  @JsonView(Views.Public.class)
  String result;

  /**
   * Construct a new AjaxResponseBody.
   * 
   * @param code The status code.
   * @param msg The message.
   * @param result The result/response.
   */
  public AjaxResponseBody(String code, String msg, String result) {
    this.code = code;
    this.result = result;
    this.msg = msg;
  }

  public AjaxResponseBody(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public AjaxResponseBody(String code) {
    this.code = code;
  }

  public AjaxResponseBody() {}

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

}
