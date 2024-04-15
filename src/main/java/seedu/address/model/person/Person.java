package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.amount.Amount;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.Sessions;
import seedu.address.model.cca.Cca;
import seedu.address.model.roles.Role;


/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Role> roles = new HashSet<>();
    private final Set<Cca> ccas = new HashSet<>();
    private final Amount amount;
    private final Attendance attendance;
    private final Sessions sessions;

    private final Optional<Metadata> metadata;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Role> roles, Set<Cca> ccas, Amount amount,
                  Attendance attendance, Sessions sessions, Optional<Metadata> metadata) {
        requireAllNonNull(name, phone, email, address, roles, ccas, amount, attendance, sessions, metadata);

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.roles.addAll(roles);
        this.ccas.addAll(ccas);
        this.amount = amount;
        this.attendance = attendance;
        this.sessions = sessions;
        this.metadata = metadata;
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Role> roles, Set<Cca> ccas, Amount amount,
                  Attendance attendance, Sessions sessions, Metadata metadata) {
        this(name, phone, email, address, roles, ccas, amount, attendance, sessions, Optional.ofNullable(metadata));
    }

    /**
     * Replaces {@code Person.ccas} with another {@code Set<Cca>}
     * by creating another Person with the new {@code Set<Cca>}.
     * This is used in AddressBook to replace {@code Person.ccas}
     * with objects in AddressBook.ccas.
     * @return new person
     */
    public Person replaceCca(Set<Cca> newCcas) {
        Set<Role> newRoles = roles;
        // If there's no more ccas, delete all the roles as well.
        if (newCcas.isEmpty()) {
            newRoles = Collections.emptySet();
        }
        return new Person(name, phone, email, address, newRoles, newCcas, amount, attendance, sessions, metadata);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable role set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    /**
     * Returns an immutable CCA set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Cca> getCcas() {
        return Collections.unmodifiableSet(ccas);
    }

    /**
     * Returns the amount owed by the person.
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Returns the Metadata of the person.
     */
    public Optional<Metadata> getMetadata() {
        return metadata;
    }

    /**
     * Returns a new Person object with the amount set to the given amount.
     */
    public Amount setAmount(Amount amount) {
        return amount;
    }

    public Attendance getAtt() {
        return attendance;
    }

    public Sessions getSess() {
        return sessions;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && roles.equals(otherPerson.roles)
                && ccas.equals(otherPerson.ccas)
                && otherPerson.getAmount().equals(getAmount())
                && attendance.equals(otherPerson.attendance)
                && sessions.equals(otherPerson.sessions)
                && metadata.equals(otherPerson.metadata);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, roles, ccas, amount, attendance, sessions, metadata);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("roles", roles)
                .add("CCAs", ccas)
                .add("amount", amount)
                .add("attendance", attendance)
                .add("sessions", sessions)
                .add("metadata", metadata
                    .map(m -> m.toString())
                    .orElse(Metadata.NO_METADATA_STRING))
                .toString();
    }

}
