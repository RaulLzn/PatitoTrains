    package patitotrains.model.domain;


    import raul.Model.array.Array;

    import java.io.Serializable;

    public class ContactPerson extends AbstractPerson implements Serializable {

        public ContactPerson(String names, String lastNames, Array<String> numbers) {
            super(names, lastNames, numbers);
        }

        @Override
        public String toString() {
            return "ContactPerson{" +
                    "lastNames='" + lastNames + '\'' +
                    ", names='" + names + '\'' +
                    ", numbers=" + numbers +
                    '}';
        }

    }
