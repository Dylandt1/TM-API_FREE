package fr.tmmods.tmapi.exceptions;

import java.util.UUID;

@Deprecated
public class ProfileNotFoundException extends Exception
{
    public ProfileNotFoundException(UUID uuid)
    {
        super("This profile doesn't find in §cRedisson §for §3MySQL §fserver §c!");
    }
}
