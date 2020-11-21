package utils;

import java.util.TreeMap;

public class LibraryPrint {

    private static final String sep = "||--------------------||";
    private final Library library;

    public LibraryPrint() {
        this.library = new Library();
    }

    public LibraryPrint(Library library) {
        this.library = library;
    }

    public String printStats() {
        StringBuilder str = new StringBuilder();

        str.append("Stats of the Library:").append(System.lineSeparator());
        str.append("Total number of Documents: ").append(library.getDocuments().size()).append(System.lineSeparator());

        library.getTypeOfDocuments().forEach((key, value) ->
                str.append("\t").append(key).append(": ").append(library.countDocumentsClass(value)).append(System.lineSeparator()));

        return str.toString();
    }

    public String printDocuments() {
        StringBuilder str = new StringBuilder();

        str.append("Documents of the Library:").append(System.lineSeparator());
        str.append(sep).append(System.lineSeparator()).append(System.lineSeparator());

        if (library.getDocuments().isEmpty())
            return str.append("No Documents Found!").append(System.lineSeparator()).toString();

        library.getDocuments().forEach(doc -> {
            str.append(doc);
            str.append(System.lineSeparator()).append(sep).append(System.lineSeparator());
        });

        return str.toString();
    }

    public String printDocument(int index) {
        return sep + System.lineSeparator() + System.lineSeparator()
                + library.getDocument(index)
                + System.lineSeparator() + sep + System.lineSeparator() + System.lineSeparator();
    }

    public String printDocumentWithCode(String code) {
        return sep + System.lineSeparator() + System.lineSeparator()
                + library.getDocument(code)
                + System.lineSeparator() + sep + System.lineSeparator() + System.lineSeparator();
    }

    public String printDocumentWithTitle(String title) {
        StringBuilder str = new StringBuilder();

        str.append("Documents of the Library with the title: ").append(title).append(System.lineSeparator());
        str.append(sep).append(System.lineSeparator()).append(System.lineSeparator());

        if (library.getDocumentWithTitle(title).isEmpty())
            throw new IndexOutOfBoundsException();

        library.getDocumentWithTitle(title).forEach(doc -> {
            str.append(doc);
            str.append(System.lineSeparator()).append(sep).append(System.lineSeparator()).append(System.lineSeparator());
        });

        return str.toString();
    }

    public String printAuthors() {
        StringBuilder str = new StringBuilder();

        str.append("Authors of the Library:").append(System.lineSeparator());
        str.append(sep).append(System.lineSeparator()).append(System.lineSeparator());

        if (library.getAuthors().isEmpty())
            return str.append("No Authors Found!").append(System.lineSeparator()).toString();

        library.getAuthors().forEach(auth -> {
            str.append(auth);
            str.append(System.lineSeparator()).append(sep).append(System.lineSeparator());
        });

        return str.toString();
    }

    public String printAuthor(int index) {
        return sep + System.lineSeparator() + System.lineSeparator()
                + library.getAuthor(index)
                + System.lineSeparator() + sep + System.lineSeparator() + System.lineSeparator();
    }

    public String printAuthor(String name) {
        return sep + System.lineSeparator() + System.lineSeparator()
                + library.getAuthor(name)
                + System.lineSeparator() + sep + System.lineSeparator() + System.lineSeparator();
    }

}
