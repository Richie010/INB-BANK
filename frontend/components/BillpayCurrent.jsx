import { useState, useEffect } from "react";
import { Dialog, DialogBackdrop, DialogPanel, DialogTitle } from '@headlessui/react'
import { ExclamationTriangleIcon, XMarkIcon } from '@heroicons/react/24/outline'
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

export function BillPayCurrent() {
    let [accountId, setAccountId] = useState("");
    let [amount, setAmount] = useState("");
    let [password, setPassword] = useState("");
    let [success, setSuccess] = useState(false);
    let [message, setMessage] = useState("");
    const [open, setOpen] = useState(false);

    const handlePanelOpen = () => {
        setOpen(true);
    }

    useEffect(() => {
        // Retrieve the accountId from local storage when the component mounts
        const storedAccountId = localStorage.getItem("accountId");
        if (storedAccountId) {
            setAccountId(storedAccountId);
        } else {
            setMessage("No account ID found in local storage.");
        }
    }, []);

    let handleSubmit = (event) => {
        event.preventDefault();
        let store_url = `http://localhost:8082/currentaccount/${accountId}/epay`;
        axios.post(store_url, { "accountId": accountId, "amount": amount,"password":password })
            .then(s => {
                console.log(s);
                setMessage(`Withdrawal Success! Balance is: ${s.data.balance},Overdraft-Balance: ${s.data.
                    overdraftLimit}`);
                setSuccess(true);
                clearForm();
            }).catch(e => {
                console.log(e);
                setMessage(e.response.data || "There was an error with your submission.");
                setSuccess(false);
            });
    };

    let clearForm = () => {
        setAmount("");
        setPassword("");
    };

    const BillCards = [
        {
            id: 1,
            name: "Electricity Bill",
            img_url: "https://th.bing.com/th/id/OIP.CdgIXbZYb1fYkt5e4Io_kAHaHa?w=194&h=194&c=7&r=0&o=5&dpr=1.5&pid=1.7",
        },
        {
            id: 2,
            name: "Phone Bill",
            img_url: "https://th.bing.com/th/id/OIP.xv_OElmvZyyTgMLYHGs86wHaFj?w=202&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
        },
        {
            id: 3,
            name: "Insurance Bill",
            img_url: "https://th.bing.com/th/id/OIP.YmcMdtzKIluAgXXR9bWIWgHaH0?w=169&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
        },
    ]

    return (

        <div className="grid grid-cols-3 gap-4 md:grid-cols-3 sm:grid-cols-1">
            {BillCards.map((card) => (
                <div key={card.id} className="bg-white drop-shadow-md rounded-md p-4">
                    <img className="w-full object-cover h-[15rem] mb-2" src={card.img_url} />
                    <p className="font-semibold">{card.name}</p>
                    <button
                        onClick={handlePanelOpen}
                        type="button"
                        className="rounded-md mt-2 bg-indigo-600 px-4 py-1.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                    >
                        Pay Now
                    </button>
                </div>
            ))}
            <Dialog open={open} onClose={setOpen} className="relative z-10">
                <DialogBackdrop
                    transition
                    className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity data-[closed]:opacity-0 data-[enter]:duration-300 data-[leave]:duration-200 data-[enter]:ease-out data-[leave]:ease-in"
                />

                <div className="fixed inset-0 z-10 w-screen overflow-y-auto">
                    <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
                        <DialogPanel
                            transition
                            className="relative bg-transparent transform overflow-hidden rounded-lg px-4 pb-4 pt-5 text-left transition-all data-[closed]:translate-y-4 data-[closed]:opacity-0 data-[enter]:duration-300 data-[leave]:duration-200 data-[enter]:ease-out data-[leave]:ease-in sm:my-8 sm:w-full sm:max-w-xl sm:p-6 data-[closed]:sm:translate-y-0 data-[closed]:sm:scale-95 ml-[10rem]"
                        >

                            <div className="flex min-h-full flex-1 flex-col justify-center py-6 sm:px-6 lg:px-8">
                                {/* <div className="sm:mx-auto sm:w-full sm:max-w-md">

                                </div> */}

                                <div className=" relative mt-10 sm:mx-auto sm:w-full sm:max-w-[480px]">
                                    <div className="bg-white px-4 py-6 shadow sm:rounded-lg sm:px-8">
                                        <h2 className="my-4 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                                            Bill payments
                                        </h2>
                                        <div className="absolute right-0 top-0 hidden pr-4 pt-4 sm:block">
                                            <button
                                                type="button"
                                                onClick={() => setOpen(false)}
                                                className="rounded-md bg-white text-gray-400 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                                            >
                                                <span className="sr-only">Close</span>
                                                <XMarkIcon aria-hidden="true" className="h-6 w-6" />
                                            </button>
                                        </div>
                                        <form onSubmit={handleSubmit} className="space-y-6">
                                            {message && (
                                                <div className="text-sm font-medium leading-6 text-gray-900">
                                                    {message}
                                                </div>
                                            )}
                                            <div>
                                                <label htmlFor="AmountToWithdraw" className="block text-sm font-medium leading-6 text-gray-900">
                                                    Amount to pay
                                                </label>
                                                <div className="mt-2">
                                                    <input
                                                        id="AmountToWithdraw"
                                                        name="AmountToWithdraw"
                                                        type="text"
                                                        required
                                                        value={amount}
                                                        onChange={(event) => setAmount(event.target.value)}
                                                        autoComplete="AmountToWithdraw"
                                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                                    />
                                                </div>
                                            </div>
                                            <div>
                                                <label htmlFor="AmountToWithdraw" className="block text-sm font-medium leading-6 text-gray-900">
                                                    Pin
                                                </label>
                                                <div className="mt-2">
                                                    <input
                                                        id="pin"
                                                        name="pin"
                                                        type="password"
                                                        required
                                                        value={password}
                                                        onChange={(event) => setPassword(event.target.value)}

                                                        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                                    />
                                                </div>
                                            </div>

                                            <div>
                                                <button
                                                    type="submit"
                                                    className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                                                >
                                                    Pay
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </DialogPanel>
                    </div>
                </div>
            </Dialog>
        </div>
    );
}
