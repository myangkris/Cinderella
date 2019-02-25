import React from 'react'
import {Icon} from 'antd'
import FooterImage from '../assets/design_elements/footer image.png'

export class Footer extends React.Component{
    render() {
        return(
            <section className="footer">
                <div className="footer-top">
                    <div className="footer-img-container">
                        <img src={FooterImage} alt=""/>
                    </div>
                    <div className="footer-connect">
                        <h1>CONNECT</h1>
                        <div className="icon-group">
                            <Icon type="facebook" style={{fontSize: '1.5rem'}} />
                            <Icon type="google" style={{fontSize: '1.5rem'}}/>
                            <Icon type="instagram" style={{fontSize: '1.5rem'}}/>
                        </div>
                    </div>
                </div>
                <div className="footer-copyright">
                    &copy; 2019 Cinderella
                </div>
            </section>
        )
    }
}