import React, { useState, useContext } from "react";
import { useNavigate, Link } from "react-router-dom";
import AuthContext from "../Context/AuthContext";
import { toast } from "react-toastify";

const Register = () => {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [role, setRole] = useState("CUSTOMER");
    const [isLoading, setIsLoading] = useState(false);
    const { register } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            toast.error("Passwords do not match!");
            return;
        }

        if (password.length < 6) {
            toast.error("Password must be at least 6 characters!");
            return;
        }

        setIsLoading(true);
        try {
            await register(name, email, password, role);
            toast.success("Registration successful!");
            navigate("/");
        } catch (error) {
            toast.error(
                error.response?.data || "Registration failed. Please try again."
            );
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="container mt-5 pt-5">
            <div className="row justify-content-center">
                <div className="col-md-5">
                    <div className="card shadow">
                        <div className="card-body p-4">
                            <h3 className="card-title text-center mb-4">Register</h3>
                            <form onSubmit={handleSubmit}>
                                <div className="mb-3">
                                    <label htmlFor="name" className="form-label">
                                        Full Name
                                    </label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="name"
                                        value={name}
                                        onChange={(e) => setName(e.target.value)}
                                        required
                                        placeholder="Enter your full name"
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="email" className="form-label">
                                        Email
                                    </label>
                                    <input
                                        type="email"
                                        className="form-control"
                                        id="email"
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                        required
                                        placeholder="Enter your email"
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="password" className="form-label">
                                        Password
                                    </label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="password"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        required
                                        placeholder="At least 6 characters"
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="confirmPassword" className="form-label">
                                        Confirm Password
                                    </label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="confirmPassword"
                                        value={confirmPassword}
                                        onChange={(e) => setConfirmPassword(e.target.value)}
                                        required
                                        placeholder="Confirm your password"
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="role" className="form-label">
                                        Account Type
                                    </label>
                                    <select
                                        className="form-select"
                                        id="role"
                                        value={role}
                                        onChange={(e) => setRole(e.target.value)}
                                    >
                                        <option value="CUSTOMER">Customer</option>
                                        <option value="SELLER">Seller</option>
                                        <option value="ADMIN">Admin</option>
                                    </select>
                                </div>
                                <button
                                    type="submit"
                                    className="btn btn-primary w-100"
                                    disabled={isLoading}
                                >
                                    {isLoading ? (
                                        <>
                                            <span
                                                className="spinner-border spinner-border-sm me-2"
                                                role="status"
                                            ></span>
                                            Registering...
                                        </>
                                    ) : (
                                        "Register"
                                    )}
                                </button>
                            </form>
                            <hr />
                            <p className="text-center mb-0">
                                Already have an account?{" "}
                                <Link to="/login" className="text-decoration-none">
                                    Login here
                                </Link>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Register;
