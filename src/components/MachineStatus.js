import React from 'react'
import Available from '../assets/design_elements/machine - available.png'
import Occupied from '../assets/design_elements/machine - in use.png'
import Malfunction from '../assets/design_elements/machine - fixing.png'
import { Popover, Button } from 'antd';
import {Link} from 'react-router-dom'
import {withRouter} from 'react-router-dom'

const popover = {
    color: '#fff',
    border: 'none',
    backgroundColor: '#048ba8',
}

/*const content = (
        //<Button style={popover}><Link to="/confirm">Reserve me!</Link></Button>
    //<button style={popover} machineID={this.props.MachineID}>Reserve me!</button>
);*/


export class MachineStatus extends React.Component {

    state = {
        machineID: ""
    }
    /*getMachineStatus = () => {
        if (this.props.machineStatus === 0) {
            return <Popover placement="top" content={content} arrowPointAtCenter><img src={Available} alt=""/></Popover>
        } else if (this.props.machineStatus === 1) {
            return <img src={Occupied} alt=""/>
        } else if (this.props.machineStatus === 2) {
            return <img src={Malfunction} alt=""/>
        }
    }*/
    getMachineStatus = () => {
        if (this.props.machineStatus === 0) {
            //return <Link to="confirm">< img onClick={(machineID, userId) => {this.handleClick(machineID, userId)}} src={Available} alt=""/></Link>

            //return <Link to="confirm">< img onClick={(machineID, userId) => {this.handleClick(machineID, userId)}} src={Available} alt=""/></Link>

            /*let data = {machineID: this.props.machineID};
            let path = {
                pathname: '/confirm/',
                query: data,
            }
            console.log(data)
            this.props.history.push(path)
            return <Link to={path}>
                    < img onClick={this.handleClick} src={Available} alt=""/>
                    </Link>*/
            return <Link to="track">< img onClick={this.fetchData} src={Available} alt=""/></Link>


        } else if (this.props.machineStatus === 1) {
            return < img src={Occupied} alt=""/>
        } else if (this.props.machineStatus === 2) {
            return < img src={Malfunction} alt=""/>
        }
    }

    itemClick(e, machineID, userId) {
        console.log("MACHINE ID = " + e.target.getAttribute('machineID'));
        //this.setState({machineID: e.target.getAttribute('machineID'),})
    }

    handleClick(machineID, userId) {
        //console.log(JSON.parse(localStorage.getItem("user_info")))
        console.log("MACHINE_ID： " + this.props.machineID)
        this.props.router.push({ state: { machineID: this.props.machineID} } )
        machineID=this.props.machineID
    }

    fetchData = () => {
        fetch(`http://localhost:8080/Cinderella/washing`, {
            method: "POST",
            dataType: "JSON",
            body: JSON.stringify({
                washingDuration: 50000,
                userId: 222,
                machineId: this.props.machineID,
            }),
        })
            .then((resp) => {
                return resp.json()
            })
            .then((data) => {
                this.setState({ suggestion: data.suggestion })
            })
            .catch((error) => {
                console.log(error, "catch the hoop")
            })
    }

    render() {

        return(
            <section className="machine-wrapper">
                {this.getMachineStatus()}
            </section>
        )
    }
}
export default withRouter(MachineStatus)