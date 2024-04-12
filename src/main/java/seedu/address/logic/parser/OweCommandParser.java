// Some of the code is inspired by the AddCommandParser.java. All credits and thanks to the original
// CS2103T teaching team for this.

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OweCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.amount.Amount;

/**
 * Parses input arguments and creates a new OweCommand object
 **/
public class OweCommandParser implements Parser<OweCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the OweCommand
     * and returns an OweCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public OweCommand parse(String args) throws ParseException {
        requireNonNull(args);
        args = args.toLowerCase();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX
                + "\n" + OweCommand.MESSAGE_USAGE, pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_AMOUNT);

        if (argMultimap.getValue(PREFIX_AMOUNT).isEmpty()) {
            throw new ParseException(OweCommand.MESSAGE_NOT_OWE);
        }

        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());

        return new OweCommand(index, amount);
    }
}
