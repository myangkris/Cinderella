import React from 'react'
import { API_ROOT } from '../constants'
import { Form, Icon, Input, Button, Checkbox, message } from 'antd';
import { Link } from 'react-router-dom';

class NormalLoginForm extends React.Component {
    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
                fetch(`${API_ROOT}/login`, {
                    method: 'POST',
                    body: JSON.stringify({
                        username: values.username,
                        password: values.password,
                    }),
                }).then((response) => {
                    if (response.ok) {
                        return response.text();
                    }
                    throw new Error(response.statusText);
                }).then((data) => {
                    message.success('Login Success!');
                    console.log(data);
                    //this.props.history.push('/home');
                    //this.props.handleSuccessfulLogin(data);
                }).catch((e) => {
                    console.log(e);
                    message.error('Login Failed.');
                });
            }
        });
    }

    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <Form onSubmit={this.handleSubmit} className="login-form">
                <Form.Item>
                    {getFieldDecorator('username', {
                        rules: [{ required: true, message: 'Please input your username!' }],
                    })(
                        <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Email/Phone Number" />
                    )}
                </Form.Item>
                <Form.Item>
                    {getFieldDecorator('password', {
                        rules: [{ required: true, message: 'Please input your Password!' }],
                    })(
                        <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password" placeholder="Password" />
                    )}
                </Form.Item>
                <Form.Item>
                    {getFieldDecorator('remember', {
                        valuePropName: 'checked',
                        initialValue: true,
                    })(
                        <Checkbox>Remember me</Checkbox>
                    )}
                    <a className="login-form-forgot" href="">Forgot password?</a>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit" className="sign-in-button">
                        Log In
                    </Button>
                    <p>
                        Don't have an account? Sign up today
                    </p>
                    <Button type="primary" htmlType="submit" className="sign-up-button">
                        <Link to="/register">Register Now!</Link>
                    </Button>
                </Form.Item>
            </Form>
        );
    }
}

export const Login = Form.create({ name: 'normal_login' })(NormalLoginForm);
