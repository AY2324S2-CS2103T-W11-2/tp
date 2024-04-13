package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.amount.Amount;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.Sessions;
import seedu.address.model.cca.Cca;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Metadata;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.roles.Role;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static String innerTrim(String string) {
        return String.join(" ", string.split("\\s+"));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = innerTrim(name.trim());
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = innerTrim(address.trim());
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String roles} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code roles} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = innerTrim(role.trim());
        if (!Role.isValidRoleName(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses a {@code String CCA} into a {@code CCA}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code CCA} is invalid.
     */
    public static Cca parseCca(String ccaString) throws ParseException {
        requireNonNull(ccaString);
        String trimmedCca = innerTrim(ccaString.trim());
        if (!Cca.isValidCcaName(trimmedCca)) {
            throw new ParseException(Cca.MESSAGE_CONSTRAINTS);
        }
        return new Cca(trimmedCca);
    }

    /**
     * Parses {@code Collection<String> roles} into a {@code Set<Role>}.
     */
    public static Set<Role> parseRoles(Collection<String> roles) throws ParseException {
        requireNonNull(roles);
        final Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            roleSet.add(parseRole(roleName));
        }
        return roleSet;
    }

    /**
     * Parses a {@code String amount} into a {@code Amount}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code amount} is invalid.
     */
    public static Amount parseAmount(String amount) throws ParseException {
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        if (!Amount.isValidAmount(trimmedAmount)) {
            throw new ParseException(Amount.MESSAGE_CONSTRAINTS);
        }
        return new Amount(trimmedAmount);
    }

    /**
     * Parses {@code Collection<String> CCAs} into a {@code Set<CCA>}.
     */
    public static Set<Cca> parseCcas(Collection<String> ccas) throws ParseException {
        requireNonNull(ccas);
        final Set<Cca> ccaSet = new HashSet<>();
        for (String ccaName : ccas) {
            ccaSet.add(parseCca(ccaName));
        }
        return ccaSet;
    }

    /**
     * Parses a {@code String attendance} into an {@code attendance}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code attendance} is invalid.
     */
    public static Attendance parseAtt(String attendance) throws ParseException {
        requireNonNull(attendance);
        String trimmedAtt = attendance.trim();
        if (!Attendance.isValidAttendance(trimmedAtt)) {
            throw new ParseException(Attendance.MESSAGE_CONSTRAINTS);
        }
        return new Attendance(attendance);
    }

    /**
     * Parses a {@code String sessions} into an {@code sessions}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sessions} is invalid.
     */
    public static Sessions parseSess(String sessions) throws ParseException {
        requireNonNull(sessions);
        String trimmedSess = sessions.trim();
        if (!Sessions.isValidSessions(trimmedSess)) {
            throw new ParseException(Sessions.MESSAGE_CONSTRAINTS);
        }
        return new Sessions(sessions);
    }

    /**
     * Parses a {@code String sessions} into an {@code sessions}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * Parses {@code Metadata metadata} into a {@code Metadata}.
     */
    public static Metadata parseMetadata(String metadata) throws ParseException {
        requireNonNull(metadata);
        String trimmedMetadata = innerTrim(metadata.trim());
        if (!Metadata.isValidMetadata(trimmedMetadata)) {
            throw new ParseException(Metadata.MESSAGE_CONSTRAINTS);
        }
        return new Metadata(trimmedMetadata);
    }
}
