import { useState } from "react";
import axios from "axios";
import { PhotoIcon, UserCircleIcon } from '@heroicons/react/24/solid'

export default function RegisterModule() {
    // State management
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const[password,setPassword]=useState("");
    const [city, setCity] = useState("");
    let [state, setState] = useState("");
    const [accountType, setAccountType] = useState("");
    const [balance, setBalance] = useState("");
    const [idproof, setIdproof] = useState("");
    const [phone, setPhone] = useState("");
    const [dob, setDob] = useState("");
    const[overdraft,setOverdraft]=useState("");
    const [message, setMessage] = useState("");
    const [success, setSuccess] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();

        const store_url = 'http://localhost:8082/api/registration';
        axios.post(store_url, {
            name,
            email,
            password,
            city,
            state,
            accountType,
            balance,
            idproof,
            phone,
            dob
        })
        .then(response => {
            console.log(response);
            setMessage(`Success! Your User ID is: ${response.data.userId}`);
            setSuccess(true);
            clearForm();
        })
        .catch(error => {
            console.log(error);
            setMessage("There was an error with your submission.");
            setSuccess(false);
        });
    };

    const clearForm = () => {
        setName("");
        setPassword("")
        setEmail("");
        setCity("");
        setState("");
        setAccountType("");
        setBalance("");
        setIdproof("");
        setPhone("");
        setDob("");
    };

    return (
        <div className="space-y-10 divide-y divide-gray-900/10">
            <div className="grid grid-cols-1 gap-x-8 gap-y-8 pt-10 md:grid-cols-3">
                <div className="px-4 sm:px-0">
                    <h2 className="text-sm font-medium text-white">Personal Information</h2>
                    {message && (
                <div className={`mt-4 p-4 ${success ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'} border border-${success ? 'green' : 'red'}-400 rounded`}>
                    {message}
                </div>
            )}

                </div>

                <form className="bg-gray-800 shadow-sm ring-1 ring-gray-900/5 sm:rounded-xl md:col-span-2" onSubmit={handleSubmit}>
                    <div className="px-4 py-6 sm:p-8">
                        <div className="grid max-w-2xl grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                            <div className="sm:col-span-3">
                                <label htmlFor="first-name" className="text-sm font-medium text-white">
                                    First name
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="name"
                                        name="name"
                                        type="text"
                                        value={name}
                                        onChange={(e) => setName(e.target.value)}
                                        autoComplete="given-name"
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                            </div>

        

                            <div className="sm:col-span-4">
                                <label htmlFor="email" className="text-sm font-medium text-white">
                                    Email address
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="email"
                                        name="email"
                                        type="email"
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                        autoComplete="email"
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                            </div>
                            <div className="sm:col-span-4">
                                <label htmlFor="email" className="text-sm font-medium text-white">
                                    Password
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="password"
                                        name="password"
                                        type="password"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        autoComplete="password"
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                            </div>

                           
                           

                           

                            <div className="sm:col-span-2 sm:col-start-1">
                                <label htmlFor="city" className="text-sm font-medium text-white">
                                    City
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="city"
                                        name="city"
                                        type="text"
                                        value={city}
                                        onChange={(e) => setCity(e.target.value)}
                                        autoComplete="address-level2"
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                            </div>

                            <div className="sm:col-span-2">
                                <label htmlFor="region" className="text-sm font-medium text-white">
                                    State
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="state"
                                        name="state"
                                        type="text"
                                        value={state}
                                        onChange={(e) => setState(e.target.value)}
                                        autoComplete="address-level1"
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                            </div>

                           

                            <div className="sm:col-span-3">
                                <label htmlFor="account-type" className="text-sm font-medium text-white">
                                    Account Type
                                </label>
                                <div className="mt-2">
                                    <select
                                        id="account-type"
                                        name="accountType"
                                        value={accountType}
                                        onChange={(e) => setAccountType(e.target.value)}
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    >
                                        <option>Select the Account</option>
                                <option>SAVINGS</option>
                                <option>CURRENT</option>
                                    </select>
                                </div>
                            </div>

                            <div className="sm:col-span-3">
                                <label htmlFor="balance" className="text-sm font-medium text-white">
                                    Balance
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="balance"
                                        name="balance"
                                        type="number"
                                        value={balance}
                                        onChange={(e) => setBalance(e.target.value)}
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                            </div>
                         

                            <div className="sm:col-span-3">
                                <label htmlFor="idproof" className="text-sm font-medium text-white">
                                    ID Proof
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="idproof"
                                        name="idproof"
                                        type="text"
                                        value={idproof}
                                        onChange={(e) => setIdproof(e.target.value)}
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                            </div>

                            <div className="sm:col-span-3">
                                <label htmlFor="phone" className="text-sm font-medium text-white">
                                    Phone Number
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="phone"
                                        name="phone"
                                        type="text"
                                        value={phone}
                                        onChange={(e) => setPhone(e.target.value)}
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                            </div>

                            <div className="sm:col-span-3">
                                <label htmlFor="dob" className="text-sm font-medium text-white">
                                    Date of Birth
                                </label>
                                <div className="mt-2">
                                    <input
                                        id="dob"
                                        name="dob"
                                        type="date"
                                        value={dob}
                                        onChange={(e) => setDob(e.target.value)}
                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="flex items-center justify-end gap-x-6 border-t border-gray-900/10 px-4 py-4 sm:px-8">
                        <button
                            type="submit"
                            className="rounded-md bg-white-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-gray-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                        >
                            Register
                        </button>
                    </div>
                </form>
            </div>
            
        </div>
    );
}
