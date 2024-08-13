import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import { useAuth } from "./AuthContext";
import './Dummy.css';

export function AdminRegister() {
    const [isActive, setIsActive] = useState(true); // Set to true to display form initially
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [success, setSuccess] = useState(false);
    const navigate = useNavigate();
    const { login } = useAuth();

   
    let handleSubmit = (event) => {
        event.preventDefault();

        let store_url = 'http://localhost:8082/api/admin/login';
        axios.post(store_url,{"username":username,"password":password,})
            .then(s => {
                console.log(s);
                setMessage(`Welcome Admin: ${s.data.username}`);
                setSuccess(true);
                login();
                clearForm();
                // login('admin');
                navigate("/AdminDashboard")
            }).catch(e => {
                console.log(e);
                setMessage("There was an error with your submission.");
                setSuccess(false);
            });
        }
        


    const clearForm = () => {
        setUsername("");
        setPassword("");
    };

    const handleToggle = () => {
        setIsActive(!isActive);
    };

    return (
        <div className={`container mt-3`} id="container">
            <div className={`form-container`}>
                {isActive && (
                    <form onSubmit={handleSubmit} className="">
                        <h1 className="text-2xl font-bold text-white">Admin Login</h1>
                        <div className='w-100'>
                            <div>
                                <label htmlFor="username" className="text-sm font-medium text-white">
                                    Enter Your Username
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="username"
                                        name="username"
                                        type="text"
                                        value={username}
                                        onChange={(event) => setUsername(event.target.value)}
                                        required
                                        autoComplete="username"
                                        className="block w-full text-black rounded-md border-gray-300 shadow-sm ring-1 ring-gray-300 placeholder-gray-400 focus:ring-2 focus:ring-indigo-600 sm:text-sm"
                                    />
                                </div>
                            </div>
                            <div>
                                <label htmlFor="password" className="text-sm font-medium text-white">
                                    Password
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="password"
                                        name="password"
                                        type="password"
                                        value={password}
                                        onChange={(event) => setPassword(event.target.value)}
                                        required
                                        autoComplete="current-password"
                                        className="block w-full text-black rounded-md border-gray-300 shadow-sm ring-1 ring-gray-300 placeholder-gray-400 focus:ring-2 focus:ring-indigo-600 sm:text-sm"
                                    />
                                </div>
                            </div>
                            <div className='mt-3'>
                                <button
                                    type="submit"
                                    className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                                >
                                    Login
                                </button>
                            </div>
                        </div>
                    </form>
                )}
            </div>

            <div className="toggle-container">
                <div className="toggle">
                    <div className="toggle-panel toggle-left">
                        <h1>Welcome Back!</h1>
                        <p>Login with your account details to use all of site features</p>
                        <button onClick={handleToggle}>Savings Account</button>
                    </div>
                    <div className="toggle-panel toggle-right">
                        <h1>Hello, Admin!</h1>
                        <p>Login with your Credentials to use all of site features</p>
                        
                    </div>
                </div>
            </div>
        </div>
    );
}
