package ecourse.dto;

public class AdminUserActionDto {

  private long id;

  public AdminUserActionDto(long id) {
    this.id = id;
  }

  public AdminUserActionDto() {}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

}
