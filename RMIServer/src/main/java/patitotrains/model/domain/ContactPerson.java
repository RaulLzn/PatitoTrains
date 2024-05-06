    package patitotrains.model.domain;

    import raul.Model.array.Array;

    import java.io.Serializable;

    /**
     * Clase que representa una persona de contacto.
     */
    public class ContactPerson extends AbstractPerson implements Serializable {

        /**
         * Constructor de la clase.
         *
         * @param names      Nombres de la persona.
         * @param lastNames  Apellidos de la persona.
         * @param numbers    Números de contacto de la persona.
         */
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
