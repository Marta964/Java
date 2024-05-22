import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router";

export default function AddRate(){
    let navigate = useNavigate();

    const [r,setRate] = useState({
        data:"",
        from:"",
        to:"",
        rate:"",
    });

    const [errors, setErrors] = useState({});

    const {data,from,to,rate} = r;

    const validateInputs = () =>{
        const errors = {};

        if(!data) { 
            errors.data = 'Data is required';
        } else if (typeof data !== 'string'){
            errors.data = 'Data must be a string';
        }




        setErrors(errors);

        return Object.keys(errors).length === 0;
    }

    const onInputChange=(e)=>{
        setRate({...r,[e.target.name]:e.target.value});
    };


    const onSubmit=async (e)=>{
        e.preventDefault();
        if(validateInputs()){
            try{
                await axios.post("http://localhost:8080/exchangerate/body",r);
                navigate("/");
            } catch (error){
                console.error("Error",error);
            }
        }
    };

    return(
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Add Rate</h2>

                    <form onSubmit={(e)=>onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Data" className="form-label">
                             Data
                            </label>
                            <input
                            type={"text"}
                            className="form-control"
                            placeholder="Enter data"
                            name="data"
                            value={data}
                            onChange={(e)=> onInputChange(e)}
                            />
                            {errors.data && <div className="text-danger">{errors.data}</div>}
                        </div>
                        <div className="mb-3">
                        <label htmlFor="From" className="form-label">
                                From
                            </label>
                            <input
                            type={"text"}
                            className="form-control"
                            placeholder="Enter from"
                            name="from"
                            value={from}
                            onChange={(e)=> onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                        <label htmlFor="To" className="form-label">
                                To
                            </label>
                            <input
                            type={"text"}
                            className="form-control"
                            placeholder="Enter to"
                            name="to"
                            value={to}
                            onChange={(e)=> onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                        <label htmlFor="Rate" className="form-label">
                                Rate
                            </label>
                            <input
                            type={"text"}
                            className="form-control"
                            placeholder="Enter rate"
                            name="rate"
                            value={rate}
                            onChange={(e)=> onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary">
                            Save
                        </button>
                
                    </form>
                </div>
            </div>
        </div>
    )
}

/*  <Link className="btn btn-outline-danger mx-2" to="/">
                            Cancel
                        </Link> */