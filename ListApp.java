public interface ListApp {

    public void create();

    public void details();

    public void delete();

    /*
      A Note About Inheritance and Similar Code:
      
      The create(), details(), and delete() methods are very similar between the classes that implement ListApp. The reason why we don't make ListApp a superclass that allows for easier implementation is because the methods are similar, but NOT the same. And the subtle differences aren't as easily solved with just providing parameters to fill in. Thus, we decided to make this an interface.

    */

}
   
