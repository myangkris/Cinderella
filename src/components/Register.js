import React from 'react';
import { Form, Input, Button, message, Select } from 'antd';
import { API_ROOT } from '../constants';
import { Link } from 'react-router-dom';
import {Footer} from './Footer';
import HeaderLogo from "../assets/design_elements/logo 2.png";
import {HowItWorksBanner} from "./HowItWorksBanner";
import {HowItWorks} from "./HowItWorks";
import {AboutBanner} from "./AboutBanner";

const { Option } = Select;


class RegistrationForm extends React.Component {
    state = {
        confirmDirty: false,
        autoCompleteResult: [],
    };

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
                // send request
                fetch(`http://localhost:8080/Cinderella/register`, {
                    method: 'POST',
                    body: JSON.stringify({
                        name: values.name,
                        password: values.password,
                        email: values.email,
                        phoneNumber: values.phoneNumber
                    }),
                }).then((response) => {
                    if (response.ok) {
                        return response.text();
                    }
                    throw new Error(response.statusText);
                })
                    .then((data) => {
                        console.log(data);
                        message.success('Registration Succeed!');
                        this.props.history.push('/');
                    })
                    .catch((e) => {
                        console.log(e);
                        message.error('Registration Failed.');
                    });
            }
        });
    }

    handleConfirmBlur = (e) => {
        const value = e.target.value;
        this.setState({ confirmDirty: this.state.confirmDirty || !!value });
    }

    compareToFirstPassword = (rule, value, callback) => {
        const form = this.props.form;
        if (value && value !== form.getFieldValue('password')) {
            callback('Two passwords that you enter is inconsistent!');
        } else {
            callback();
        }
    }

    validateToNextPassword = (rule, value, callback) => {
        const form = this.props.form;
        if (value && this.state.confirmDirty) {
            form.validateFields(['confirm'], { force: true });
        }
        callback();
    }

    render() {
        const { getFieldDecorator } = this.props.form;

        const formItemLayout = {
            labelCol: {
                xs: { span: 24 },
                sm: { span: 8 },
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 16 },
            },
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 24,
                    offset: 0,
                },
                sm: {
                    span: 16,
                    offset: 8,
                },
            },
        };


        return (
            <div>
                <div className="logo-container">
                    <img src={HeaderLogo} alt=""/>
                </div>
                <header className="Register-header"></header>
                <div className="register">
                    <Form onSubmit={this.handleSubmit}>
                    <Form.Item
                        {...formItemLayout}
                        label="Username"
                    >
                        {getFieldDecorator('username', {
                            rules: [{ required: true, message: 'Please input your username!' }],
                        })(
                            <Input />
                        )}
                    </Form.Item>
                    <Form.Item
                        {...formItemLayout}
                        label="Password"
                    >
                        {getFieldDecorator('password', {
                            rules: [{
                                required: true, message: 'Please input your password!',
                            }, {
                                validator: this.validateToNextPassword,
                            }],
                        })(
                            <Input type="password" />
                        )}
                    </Form.Item>
                    <Form.Item
                        {...formItemLayout}
                        label="Confirm Password"
                    >
                        {getFieldDecorator('confirm', {
                            rules: [{
                                required: true, message: 'Please confirm your password!',
                            }, {
                                validator: this.compareToFirstPassword,
                            }],
                        })(
                            <Input type="password" onBlur={this.handleConfirmBlur} />
                        )}
                    </Form.Item>
                    <Form.Item
                        {...formItemLayout}
                        label="Email"
                    >
                        {getFieldDecorator('email', {
                            rules: [{ required: true, message: 'Please input your email!' }],
                        })(
                            <Input />
                        )}
                    </Form.Item>
                    <Form.Item
                        {...formItemLayout}
                        label="Phone Number"
                    >
                        {getFieldDecorator('phoneNumber', {
                            rules: [{ required: true, message: 'Please input your phone number!' }],
                        })(
                            <Input style={{ width: '100%' }} />
                        )}
                    </Form.Item>
                    <Form.Item {...tailFormItemLayout}>
                        <Button type="primary" htmlType="submit" className="register-button">Sign Up</Button>
                        <p className="text">Already have account? <Link to = "">Sign in now!</Link></p>
                    </Form.Item>

                    </Form>
                </div>
                <header className="Register-header"></header>
                <HowItWorksBanner/>
                <HowItWorks/>
                <AboutBanner/>
                <Footer/>
            </div>
        );
    }
}

export const Register = Form.create({ name: 'register' })(RegistrationForm);