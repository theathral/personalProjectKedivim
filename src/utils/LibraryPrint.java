package utils;

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

        str.append("Stats of the Library:\n");
        str.append("Total number of Documents: ").append(library.getDocuments().size()).append("\n");

        library.getTypeOfDocuments().entrySet().stream().sorted().forEach(entry -> {
            str.append("\t").append(entry.getKey()).append(": ").append(library.countDocumentsClass(entry.getValue())).append("\n");
        });

        return str.toString();
    }

    public String printDocuments() {
        StringBuilder str = new StringBuilder();

        str.append("Documents of the Library:\n");
        str.append(sep).append("\n\n");

        library.getDocuments().forEach(doc -> {
            str.append(doc);
            str.append("\n").append(sep).append("\n");
        });

        return str.toString();
    }

    public String printDocument(int index) {
        return sep + "\n\n"
                + library.getDocument(index)
                + "\n" + sep + "\n\n";
    }

    public String printDocumentWithCode(String code) {
        return sep + "\n\n"
                + library.getDocument(code)
                + "\n" + sep + "\n\n";
    }

    public String printDocumentWithTitle(String title) {
        StringBuilder str = new StringBuilder();

        str.append("Documents of the Library with the title: ").append(title).append("\n");
        str.append(sep).append("\n\n");

        library.getDocumentWithTitle(title).forEach(doc -> {
            str.append(doc);
            str.append("\n").append(sep).append("\n\n");
        });

        return str.toString();
    }

    public String printAuthors() {
        StringBuilder str = new StringBuilder();

        str.append("Authors of the Library:\n");
        str.append(sep).append("\n\n");

        library.getAuthors().forEach(auth -> {
            str.append(auth);
            str.append("\n").append(sep).append("\n");
        });

        return str.toString();
    }

    public String printAuthor(int index) {
        return sep + "\n\n"
                + library.getAuthor(index)
                + "\n" + sep + "\n\n";
    }

    public String printAuthor(String name) {
        return sep + "\n\n"
                + library.getAuthor(name)
                + "\n" + sep + "\n\n";
    }


}
