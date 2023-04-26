
import './App.css';
import React, {Component} from "react";
import Authors from "../Authors/authors";
import LibraryService from "../../repository/libraryRepository";




class App extends Component {
    constructor(props){
        super(props);
        this.state = {
            authors: [],
            products: [],
            categories: []
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Route path={"/authors"} exact render={() =>
                            <Authors authors={this.state.authors}/>}/>
                        {/*<Route path={"/categories"} exact render={() =>*/}
                        {/*    <Categories categories={this.state.categories}/>}/>*/}
                        {/*<Route path={"/books/add"} exact render={() =>*/}
                        {/*    <BookAdd categories={this.state.categories}*/}
                        {/*                authors={this.state.authors}*/}
                        {/*                onAddBook={this.addBook}/>}/>*/}
                        {/*<Route path={"/books/edit/:id"} exact render={() =>*/}
                        {/*    <ProductEdit categories={this.state.categories}*/}
                        {/*                 authors={this.state.authors}*/}
                        {/*                 onEditBook={this.editBook}*/}
                        {/*                 book={this.state.selectedBook}/>}/>*/}
                        {/*<Route path={"/books"} exact render={() =>*/}
                        {/*    <Products books={this.state.book}*/}
                        {/*              onDelete={this.deleteBook}*/}
                        {/*              onEdit={this.getProduct}/>}/>*/}
                        <Redirect to={"/books"}/>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () => {
        this.loadAuthors();
        this.loadCategories();
        this.loadBooks();
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, availableCopies, category, author) => {
        LibraryService.addBook(name, availableCopies, category, author)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, availableCopies, category, author) => {
        LibraryService.editBook(id, name, availableCopies, category, author)
            .then(() => {
                this.loadBooks();
            });
    }
}

export default App;


