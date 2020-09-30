import React, {Component} from 'react';
import AppNav from "./AppNav";

class Category extends Component {
    state = {
        isLoading : true,
        Categories : []
    };

    // sync -> you send a request and you have to wait
    // async -> You send a request and you don't have to wait

    async componentDidMount() {
        const response = await fetch("/api/category");
        // console.log(response);
        const body = await response.json();
        // console.log(body);
        this.setState({
           Categories : body,
           isLoading : false
        });
    }

    render() {

        const {isLoading,Categories} = this.state;
        if (isLoading)
        {
            return (
                <div>Loading...</div>
            );
        }
        return (
            <div>
                <AppNav/>
                <h2>Categories</h2>
                {
                    Categories.map(category =>
                        <div id = {category.id}>
                            {category.name}
                        </div>
                    )
                }
            </div>
        );
    }
}

export default Category;