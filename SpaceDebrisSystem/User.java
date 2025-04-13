public abstract class User {
  private String userId;
  private String name;
  private String role;

  public User(String userId, String name, String role) {
      this.userId = userId;
      this.name = name;
      this.role = role;
  }

  public abstract String[] getMenuOptions();
}
