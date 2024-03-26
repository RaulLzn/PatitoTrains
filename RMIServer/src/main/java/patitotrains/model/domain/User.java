    package patitotrains.model.domain;

    import java.io.Serializable;

    public class User implements Serializable {
        private String username;
        private String password;
        private AbstractPerson person;


        //constructor con parametros
        public User(String username, String password, AbstractPerson person) {
            this.username = username;
            this.password = password;
            this.person = person;
        }

        //Constructor vacio
        public User() {
            this.username = "";
            this.password = "";
            this.person = AbstractPerson.getEmptyPerson();
        }

        //Getters y Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public AbstractPerson getPerson() {
            return person;
        }

        public void setPerson(AbstractPerson person) {
            this.person = person;
        }

        //Devolver constructor vacio
        public static User getEmptyUser() {
            return new User();
        }
    }
