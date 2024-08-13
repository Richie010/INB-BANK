import { useState } from "react";
import axios from "axios";

import "bootstrap/dist/css/bootstrap.min.css";



export function Userlogin(){
    
    let[email,setEmail]=useState("");
    let[password,setPassword]=useState("");
  
    let [message, setMessage] = useState("");
    let [success, setSuccess] = useState(false);
    


    let handleSubmit = (event) => {
        event.preventDefault();

        let store_url = 'http://localhost:8082/api/registration/login';
        axios.post(store_url,{"email":email,"password":password,})
            .then(s => {
                console.log(s);
                setMessage(`Welcome user: ${s.data.getName}`);
                setSuccess(true);
                clearForm();
              
            }).catch(e => {
                console.log(e);
                setMessage("There was an error with your submission.");
                setSuccess(false);
            });
        }
        let clearForm = () => {
            setEmail("")
            setPassword("");
           
         }
            return(
                <div className="container d-flex justify-content-center align-items-center vh-100" style={{ background: "linear-gradient(45deg, #233 0%, #fff 100%)" }}>
                <div className="card" style={{ width: "450px", padding: "20px" }}>
                    <div className="card-body">
                        
                        {message && (
                            <div className={`alert ${success ? 'alert-success' : 'alert-danger'} text-center`}>
                                {message}
                            </div>
                        )}
                        <form onSubmit={handleSubmit}>
                            <h3>Login</h3>
                            <div className="form-group">
                                
                                <label>User Email</label>
                                <input type="email" value={email} onChange={(event) => setEmail(event.target.value)} className="form-control form-control-sm" />
                            </div>
                            <div className="form-group">
                                <label>Password</label>
                                <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} className="form-control form-control-sm" />
                            </div>
                            
                            <input type="submit" value="Login" className="btn btn-primary w-100 mt-3" />
                    
                            </form>
                            </div>
                            </div>
                            </div>
            )
}

