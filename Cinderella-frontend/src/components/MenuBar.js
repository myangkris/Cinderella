import React from 'react'
import { Menu, Icon } from 'antd';
import { Divider } from 'antd';
import { Link } from 'react-router-dom'


export class MenuBar extends React.Component {
    state = {
        current: '',
    }

    handleClick = (e) => {
        console.log('click ', e);
        this.setState({
            current: e.key,
        });
    }

    render() {
        return (
            <Menu
                onClick={this.handleClick}
                selectedKeys={[this.state.current]}
                mode="horizontal"
            >
                <Menu.Item key="reserve">
                    <Link to="/reserve"><Icon type="schedule" />Reserve</Link>
                </Menu.Item>
                <Divider type="vertical" />
                <Menu.Item key="track">
                    <Link to="/track"><Icon type="dashboard" />Track</Link>
                </Menu.Item>
                <Divider type="vertical" />
                <Menu.Item key="report">
                    <Link to="/report"><Icon type="tool" />Report</Link>
                </Menu.Item>
            </Menu>
        );
    }
}
