package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.NIL_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_METADATA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.cca.Cca;
import seedu.address.model.person.Metadata;
import seedu.address.model.person.Person;
import seedu.address.model.roles.Role;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getRoles().stream().forEach(
            s -> sb.append(PREFIX_ROLE + s.roleName + " ")
        );
        person.getCcas().stream().forEach(
            c -> sb.append(PREFIX_CCA + c.ccaName + " ")
        );
        sb.append(PREFIX_METADATA + person
            .getMetadata()
            .map(Metadata::toString)
            .orElse(Metadata.NO_METADATA_STRING) + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getRoles().isPresent()) {
            Set<Role> roles = descriptor.getRoles().get();
            if (roles.isEmpty()) {
                sb.append(PREFIX_ROLE + NIL_FIELD);
            } else {
                sb.append(PREFIX_ROLE);
                roles.forEach(s -> sb.append(PREFIX_ROLE).append(s.roleName).append(" "));
            }
        }
        sb.append(" ");
        if (descriptor.getCcas().isPresent()) {
            Set<Cca> roles = descriptor.getCcas().get();
            if (roles.isEmpty()) {
                sb.append(PREFIX_CCA + NIL_FIELD);
            } else {
                roles.forEach(s -> sb.append(PREFIX_CCA).append(s.ccaName).append(" "));
            }
        }
        sb.append(" ");
        descriptor.getMetadata().ifPresent(metadata -> sb.append(PREFIX_METADATA)
                .append(metadata.metadata).append(" "));
        return sb.toString();
    }
}
