    package patitotrains.model.domain;


    import raul.Model.array.Array;

    import java.io.Serializable;

    public class ContactPerson extends AbstractPerson implements Serializable {

        private String id;

        public ContactPerson(String id, String names, String lastNames, Array<String> numbers) {
            super(names, lastNames, numbers);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "ContactPerson{" +
                    "id='" + id + '\'' +
                    ", lastNames='" + lastNames + '\'' +
                    ", names='" + names + '\'' +
                    ", numbers=" + numbers +
                    '}';
        }

    }
