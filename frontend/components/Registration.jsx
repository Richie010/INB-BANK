import { useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

export function Register() {
    let [name, setName] = useState("");
    let [email, setEmail] = useState("");
    let [password, setPassword] = useState("");
    let [accountType, setAccountType] = useState("");
    let[balance,setBalance]=useState("");
    let [phone, setPhone] = useState("");
    let [idproof, setIdproof] = useState("");
    let [city, setCity] = useState("");
    let [state, setState] = useState("");
    let[dob,setDob]=useState("")
    let [message, setMessage] = useState("");
    let [success, setSuccess] = useState(false);

    let handleSubmit = (event) => {
        event.preventDefault();

        let store_url = 'http://localhost:8082/api/registration';
        axios.post(store_url, { "name": name, "email": email, "password": password, "accountType": accountType, "phone": phone, "idproof": idproof, "city": city, "state": state ,"balance":balance,"dob":dob},)
            .then(s => {
                console.log(s);
                setMessage(`Success! Your User ID is: ${s.data.userId}`);
                setSuccess(true);
                clearForm();
            }).catch(e => {
                console.log(e);
                setMessage("There was an error with your submission.");
                setSuccess(false);
            });
    }

    let clearForm = () => {
        setName("");
        setEmail("");
        setPassword("");
        setAccountType("");
        setBalance("");
        setPhone("");
        setIdproof("");
        setCity("");
        setState("");
    }

    return (
        <div className="container d-flex justify-content-center align-items-center vh-100" style={{ background: "linear-gradient(45deg, #fff 0%, #fff 100%)" }}>
            <div className="card" style={{ width: "450px", padding: "20px" }}>
                <div className="card-body">
                    
                    {message && (
                        <div className={`alert ${success ? 'alert-success' : 'alert-danger'} text-center`}>
                            {message}
                        </div>
                    )}
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label>UserName</label>
                            <input type="text" value={name} onChange={(event) => setName(event.target.value)} className="form-control form-control-sm" />
                        </div>
                        <div className="form-group">
                            <label>Email</label>
                            <input type="email" value={email} onChange={(event) => setEmail(event.target.value)} className="form-control form-control-sm" />
                        </div>
                        <div className="form-group">
                            <label>Password</label>
                            <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} className="form-control form-control-sm" />
                        </div>
                        <div className="form-group">
                            <label>DOB</label>
                            <input type="date" value={dob} onChange={(event) => setDob(event.target.value)} className="form-control form-control-sm" />
                        </div>
                        <div className="form-group">
                            <label>Account Type</label>
                            <select value={accountType} onChange={(event) => setAccountType(event.target.value)} className="form-control form-control-sm">
                                <option>Select the Account</option>
                                <option>SAVINGS</option>
                                <option>CURRENT</option>
                            </select>
                        </div>
                        <div className="form-group">
                            <label>Enter Amount</label>
                            <input type="number" value={balance} onChange={(event) => setBalance(event.target.value)} className="form-control form-control-sm" />
                        </div>
                        <div className="form-group">
                            <label>Id proof</label>
                            <input type="number" value={idproof} onChange={(event) => setIdproof(event.target.value)} className="form-control form-control-sm" />
                        </div>
                        <div className="form-group">
                            <label>Mobile Number</label>
                            <input type="number" value={phone} onChange={(event) => setPhone(event.target.value)} className="form-control form-control-sm" />
                        </div>
                        <div className="form-group">
                            <label>City</label>
                            <input type="text" value={city} onChange={(event) => setCity(event.target.value)} className="form-control form-control-sm" />
                        </div>
                        <div className="form-group">
                            <label>State</label>
                            <input type="text" value={state} onChange={(event) => setState(event.target.value)} className="form-control form-control-sm" />
                        </div>
                        <input type="submit" value="Register" className="btn btn-primary w-100 mt-3" />
                    </form>
                </div>
            </div>
        </div>
    );
}
