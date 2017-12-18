import React, {Component} from 'react';
import Link from '../link/Link'
import AppBar from 'material-ui/AppBar'
import MenuItem from 'material-ui/MenuItem';

class Menu extends Component {

    render() {
        return (
            <div>
                <AppBar
                    showMenuIconButton={false}
                    title="Menu"/>
                <Link to='/categories'>
                    <MenuItem>Categories</MenuItem>
                </Link>
                <Link to='/sets'>
                    <MenuItem>Sets</MenuItem>
                </Link>
                <Link to='/bricks'>
                    <MenuItem>Bricks</MenuItem>
                </Link>
                <Link to='/shapes'>
                    <MenuItem>Shapes</MenuItem>
                </Link>
                <Link to='/kits'>
                    <MenuItem>Kits</MenuItem>
                </Link>
            </div>
        )
    }
}

export default Menu
