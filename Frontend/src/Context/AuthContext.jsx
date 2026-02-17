import { createContext, useState, useEffect } from "react";
import axios from "../axios";

const AuthContext = createContext({
    user: null,
    login: async () => { },
    register: async () => { },
    logout: () => { },
    isAuthenticated: false,
    isAdmin: false,
    isSeller: false,
});

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(() => {
        const stored = localStorage.getItem("user");
        return stored ? JSON.parse(stored) : null;
    });

    useEffect(() => {
        if (user) {
            localStorage.setItem("user", JSON.stringify(user));
        } else {
            localStorage.removeItem("user");
        }
    }, [user]);

    const login = async (email, password) => {
        const response = await axios.post("/auth/login", { email, password });
        const userData = response.data;
        setUser(userData);
        return userData;
    };

    const register = async (name, email, password, role) => {
        const response = await axios.post("/auth/register", {
            name,
            email,
            password,
            role,
        });
        const userData = response.data;
        setUser(userData);
        return userData;
    };

    const logout = () => {
        setUser(null);
        localStorage.removeItem("user");
        localStorage.removeItem("cart");
    };

    const isAuthenticated = !!user && !!user.token;
    const isAdmin = isAuthenticated && user.role === "ADMIN";
    const isSeller = isAuthenticated && user.role === "SELLER";

    return (
        <AuthContext.Provider
            value={{
                user,
                login,
                register,
                logout,
                isAuthenticated,
                isAdmin,
                isSeller,
            }}
        >
            {children}
        </AuthContext.Provider>
    );
};

export default AuthContext;
