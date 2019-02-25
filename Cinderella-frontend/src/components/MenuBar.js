import React from 'react'
import { Menu, Icon } from 'antd';
import { Divider } from 'antd';


export class MenuBar extends React.Component {
    state = {
        current: 'reserve',
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
                    <Icon type="schedule" />Reserve
                </Menu.Item>
                <Divider type="vertical" />
                <Menu.Item key="track">
                    <Icon type="dashboard" />Track
                </Menu.Item>
                <Divider type="vertical" />
                <Menu.Item key="report">
                    <Icon type="tool" />Report
                </Menu.Item>
            </Menu>
        );
    }
}
